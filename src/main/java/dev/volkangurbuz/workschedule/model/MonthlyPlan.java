package dev.volkangurbuz.workschedule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "MonthlyPlan")
@Table(name = "monthly_plan")
@NoArgsConstructor
@Setter
@Getter
public class MonthlyPlan implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonIgnore
  transient Set<Worker> workerList;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonIgnore
  transient Set<Reason> exceptions;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  @OneToMany(mappedBy = "monthlyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Shift> shifts;

  public MonthlyPlan(Set<Worker> workerList, Set<Reason> exceptions, Date date) {
    this.workerList = workerList;
    this.exceptions = exceptions;
    this.createDate = date;
  }
}
