package dev.volkangurbuz.workschedule.config;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword) {
    return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return BCrypt.checkpw(encodedPassword, encodedPassword);
  }
}
