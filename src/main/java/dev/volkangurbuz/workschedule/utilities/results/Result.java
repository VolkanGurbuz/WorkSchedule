package dev.volkangurbuz.workschedule.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
  private boolean isSuccess;
  private String message;
}
