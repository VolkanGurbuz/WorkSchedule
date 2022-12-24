package controller

import dev.volkangurbuz.workschedule.model.ERole
import dev.volkangurbuz.workschedule.model.Role
import dev.volkangurbuz.workschedule.model.Worker
import dev.volkangurbuz.workschedule.payload.request.LoginRequest
import dev.volkangurbuz.workschedule.payload.request.SignupRequest
import dev.volkangurbuz.workschedule.payload.response.JwtResponse
import dev.volkangurbuz.workschedule.repositories.RoleRepository
import dev.volkangurbuz.workschedule.repositories.WorkerRepository
import dev.volkangurbuz.workschedule.security.JwtUtils
import dev.volkangurbuz.workschedule.services.WorkerDetailsImpl
import dev.volkangurbuz.workschedule.utilities.results.Result
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@CrossOrigin(origins = arrayOf("*"), maxAge = 3600)
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val roleRepository: RoleRepository,
    private val jwtUtils: JwtUtils,
    private val encoder: PasswordEncoder,
    private val workerRepository: WorkerRepository
) {

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<JwtResponse> {

        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username, loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = auth;
        val jwt = jwtUtils.generateJwtToken(auth);
        val userDetails = auth.principal as WorkerDetailsImpl
        val roles = userDetails.authorities.asSequence().map(GrantedAuthority::getAuthority).toList()
        return ResponseEntity.ok(
            JwtResponse(
                jwt, userDetails.id, userDetails.username, userDetails.email, roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signupRequest: SignupRequest): ResponseEntity<Any> {

        if (workerRepository.existsByUsername(signupRequest.username)) {
            return ResponseEntity.badRequest()
                .body(Result(false, "Error: Username is already taken!"))
        }

        if (workerRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(Result(false, "Error: Email is already in use!"));
        }

        val user = Worker(
            signupRequest.username, signupRequest.email,
            encoder.encode(signupRequest.password)
        )

        val strRoles = signupRequest.role;
        val roles: MutableSet<Role> = HashSet()

        if (strRoles == null) {
            val userRole =
                roleRepository
                    .findByName(ERole.ROLE_USER)
            roles.add(userRole.get())
        } else {
            strRoles.forEach { role ->
                if ("admin" == (role)) {
                    val adminRole =
                        roleRepository
                            .findByName(ERole.ROLE_ADMIN)
                    roles.add(adminRole.get());
                } else {
                    val userRole =
                        roleRepository
                            .findByName(ERole.ROLE_USER)
                    roles.add(userRole.get());
                }
            }
        }

        workerRepository.save(user);
        return ResponseEntity.ok(Result(true, "User registered successfully!"));
    }


}