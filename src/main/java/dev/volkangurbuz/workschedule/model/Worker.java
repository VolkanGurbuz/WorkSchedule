package dev.volkangurbuz.workschedule.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Worker {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @Column(name = "password")
  private String password;
}
