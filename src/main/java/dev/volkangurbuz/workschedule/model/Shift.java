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

@Entity(name = "Shift")
@Table(name = "shift")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shift {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @CreationTimestamp
  @JsonFormat(pattern = "dd-MM-yyyy")
  Date shiftStart;

  @CreationTimestamp
  @JsonFormat(pattern = "dd-MM-yyyy")
  Date shiftEnd;

  @ManyToOne(fetch = FetchType.LAZY)
  private MonthlyPlan monthlyPlan;
}
