package dev.volkangurbuz.workschedule.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "workers")
public class Worker {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column(name = "username")
  String username;
  @Column(name = "password")
  String password;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, name = "roles")
  ERole workerType;

  public Worker(String username, String password, ERole workerType) {
    this.username = username;
    this.password = password;
    this.workerType = workerType;
  }
}
