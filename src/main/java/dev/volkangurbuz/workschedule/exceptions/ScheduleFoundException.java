package dev.volkangurbuz.workschedule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ScheduleFoundException extends RuntimeException {

  private final String message;

  public ScheduleFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
