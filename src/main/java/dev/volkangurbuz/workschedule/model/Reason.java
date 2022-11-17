package dev.volkangurbuz.workschedule.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reason {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne Worker worker;

  EReasonLevel exceptionLevel;
  Date date;
}
