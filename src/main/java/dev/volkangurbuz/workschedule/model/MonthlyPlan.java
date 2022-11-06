package dev.volkangurbuz.workschedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
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

  @CreationTimestamp
  @JsonFormat(pattern = "dd-MM-yyyy")
  Date date;

  public MonthlyPlan(List<Worker> workerList, List<Reason> exceptions, Date date) {
    this.workerList = workerList;
    this.exceptions = exceptions;
    this.date = date;
  }
}
