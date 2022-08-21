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

  public boolean isSuccess;
  public String message;
}
