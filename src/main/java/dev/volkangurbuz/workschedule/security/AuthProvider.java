package dev.volkangurbuz.workschedule.security;

import dev.volkangurbuz.workschedule.model.ERole;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider {
  private final WorkerRepository repository;

  private final PasswordEncoder encoder;

  public AuthProvider(WorkerRepository repository, PasswordEncoder encoder) {
    this.encoder = encoder;
    this.repository = repository;
  }

  /**
   * Get the username and password from authentication object and validate with password encoders
   * matching method
   *
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    Worker worker = repository.findWorkerByUsername(username).orElse(null);
    if (worker == null) {
      throw new BadCredentialsException("Details not found");
    }

    if (encoder.matches(password, worker.getPassword())) {
      log.info("Successfully Authenticated the user");
      return new UsernamePasswordAuthenticationToken(
          username, password, getWorkerRoles(worker.getWorkerType()));
    } else {
      throw new BadCredentialsException("Password mismatch");
    }
  }

  /**
   * An user can have eRole
   *
   * @param eRole
   * @return
   */
  private List<GrantedAuthority> getWorkerRoles(ERole eRole) {
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

    grantedAuthorityList.add(new SimpleGrantedAuthority(eRole.name()));

    return grantedAuthorityList;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
