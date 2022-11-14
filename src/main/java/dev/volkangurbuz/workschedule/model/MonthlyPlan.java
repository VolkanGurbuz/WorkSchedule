package dev.volkangurbuz.workschedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "MonthlyPlan")
@Table(name = "monthly_plan")
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

  @OneToMany(mappedBy = "monthlyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Shift> shifts;

  public MonthlyPlan(List<Worker> workerList, List<Reason> exceptions, Date date) {
    this.workerList = workerList;
    this.exceptions = exceptions;
    this.date = date;
  }
}
