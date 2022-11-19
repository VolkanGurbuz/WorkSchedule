package dev.volkangurbuz.workschedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
@Getter
public class MonthlyPlan {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  List<Worker> workerList;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  List<Reason> exceptions;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  @OneToMany(mappedBy = "monthlyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Shift> shifts;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, nullable = false)
  private EMonthYear eMonthYear;

  public MonthlyPlan(List<Worker> workerList, List<Reason> exceptions, Date date) {
    this.workerList = workerList;
    this.exceptions = exceptions;
    this.createDate = date;
  }
}
