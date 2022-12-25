package dev.volkangurbuz.workschedule.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result {
  private boolean isSuccess;
  private String message;

  public Result(boolean isSuccess, String message) {
    this.isSuccess = isSuccess;
    this.message = message;
  }
}
