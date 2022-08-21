package dev.volkangurbuz.workschedule.model.dto;

import dev.volkangurbuz.workschedule.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkerDTO {
  Long id;

  String username;
  String password;
  ERole workerType;
}
