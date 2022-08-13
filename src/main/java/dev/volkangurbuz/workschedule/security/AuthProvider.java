package dev.volkangurbuz.workschedule.security;

import dev.volkangurbuz.workschedule.model.Attempts;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.AttemptsRepository;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Component
public class AuthProvider implements AuthenticationProvider {
  private static final int ATTEMPTS_LIMIT = 3;
  @Autowired private AttemptsRepository attemptsRepository;
  @Autowired private WorkerRepository workerRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
    if (userAttempts.isPresent()) {
      Attempts attempts = userAttempts.get();
      attempts.setAttempts(0);
      attemptsRepository.save(attempts);
    }
    return authentication;
  }

  private void processFailedAttempts(String username, Worker user) {
    Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
    if (userAttempts.isEmpty()) {
      Attempts attempts = new Attempts();
      attempts.setUsername(username);
      attempts.setAttempts(1);
      attemptsRepository.save(attempts);
    } else {
      Attempts attempts = userAttempts.get();
      attempts.setAttempts(attempts.getAttempts() + 1);
      attemptsRepository.save(attempts);

      if (attempts.getAttempts() + 1 > ATTEMPTS_LIMIT) {
        //        user.setAccountNonLocked(false);
        workerRepository.save(user);
        throw new LockedException("Too many invalid attempts. Account is locked!!");
      }
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }
}
