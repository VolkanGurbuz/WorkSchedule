package dev.volkangurbuz.workschedule.config;

import dev.volkangurbuz.workschedule.exceptions.EntityNotFoundException;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class WorkerAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(WorkerAuthenticationProvider.class);

    private WorkerRepository repository;

    private PasswordEncoder encoder;

    public WorkerAuthenticationProvider(WorkerRepository repository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.repository = repository;
    }

    /**
     * Get the username and password from authentication object and validate with password encoders matching method
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Worker worker = repository.findWorkerByUsername(username).orElseThrow(() -> new EntityNotFoundException("no user"));
        if (worker == null) {
            throw new BadCredentialsException("Details not found");
        }

        if (encoder.matches(password, worker.getPassword())) {
            logger.info("Successfully Authenticated the user");
            return new UsernamePasswordAuthenticationToken(username, password,  getWorkRoles(worker.getWorkerType().name()));
        } else {
            throw new BadCredentialsException("Password mismatch");
        }
    }

    /**
     * An user can have more than one roles separated by ",". We are splitting each role separately
     * @param workRoles
     * @return
     */
    private List<GrantedAuthority> getWorkRoles(String workRoles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String[] roles = workRoles.split(",");
        for (String role : roles) {
            logger.info("Role:{} ", role);
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        }

        return grantedAuthorityList;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}