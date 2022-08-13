package dev.volkangurbuz.workschedule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  private final String message;

  public EntityNotFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
