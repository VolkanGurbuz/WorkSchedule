package dev.volkangurbuz.workschedule.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@NoArgsConstructor
public class MonthlyPlan {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  List<Worker> workerList;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  List<Reason> exceptions;

  Date date;
  final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

  public MonthlyPlan(List<Worker> workerList, List<Reason> exceptions, Date date) {
    this.workerList = workerList;
    this.exceptions = exceptions;
    this.date = date;
  }
}
