package dev.volkangurbuz.workschedule.controller;

import dev.volkangurbuz.workschedule.model.ERole;
import dev.volkangurbuz.workschedule.model.Role;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.payload.request.LoginRequest;
import dev.volkangurbuz.workschedule.payload.request.SignupRequest;
import dev.volkangurbuz.workschedule.payload.response.JwtResponse;
import dev.volkangurbuz.workschedule.repositories.RoleRepository;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import dev.volkangurbuz.workschedule.security.JwtUtils;
import dev.volkangurbuz.workschedule.services.WorkerDetailsImpl;
import dev.volkangurbuz.workschedule.utilities.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * @deprecated
 * should be replaced with kotlin/controller/AuthController
 */
@Deprecated(forRemoval = true)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final RoleRepository roleRepository;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder encoder;
  private final WorkerRepository workerRepository;

  public AuthController(
      AuthenticationManager authenticationManager,
      RoleRepository roleRepository,
      JwtUtils jwtUtils,
      PasswordEncoder encoder,
      WorkerRepository workerRepository) {
    this.authenticationManager = authenticationManager;
    this.roleRepository = roleRepository;
    this.jwtUtils = jwtUtils;
    this.encoder = encoder;
    this.workerRepository = workerRepository;
  }

  @PostMapping("/signin")
  public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    WorkerDetailsImpl userDetails = (WorkerDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse(
            jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (Boolean.TRUE.equals(workerRepository.existsByUsername(signUpRequest.getUsername()))) {
      return ResponseEntity.badRequest()
          .body(new Result(false, "Error: Username is already taken!"));
    }

    if (Boolean.TRUE.equals(workerRepository.existsByEmail(signUpRequest.getEmail()))) {
      return ResponseEntity.badRequest().body(new Result(false, "Error: Email is already in use!"));
    }

    // Create new user's account
    Worker user =
        new Worker(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole =
          roleRepository
              .findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(
          role -> {
            if ("admin".equals(role)) {
              Role adminRole =
                  roleRepository
                      .findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);
            } else {
              Role userRole =
                  roleRepository
                      .findByName(ERole.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(userRole);
            }
          });
    }

    user.setRoles(roles);
    workerRepository.save(user);

    return ResponseEntity.ok(new Result(true, "User registered successfully!"));
  }
}
