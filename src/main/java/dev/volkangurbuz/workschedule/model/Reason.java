package dev.volkangurbuz.workschedule.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Reason implements Serializable {

  private static final long serialVersionUID = 2L;

  public Reason(Worker worker, Date date,EReasonLevel exceptionLevel) {
    this.worker = worker;
    this.exceptionLevel = exceptionLevel;
    this.exceptionDate= date;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @OneToOne Worker worker;

  @Column(name = "exception_date")
  Date exceptionDate;

  EReasonLevel exceptionLevel;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  Date createDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modified_date")
  Date modifiedDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "deleted_date")
  Date deletedDate;
}
