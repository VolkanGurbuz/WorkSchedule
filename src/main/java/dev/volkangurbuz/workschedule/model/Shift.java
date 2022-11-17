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
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ",
      locale = "en_GB")
  Date shiftStart;

  @CreationTimestamp
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ",
      locale = "en_GB")
  Date shiftEnd;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, nullable = false)
  private EShiftType eShiftType;

  @ManyToOne(fetch = FetchType.LAZY)
  private MonthlyPlan monthlyPlan;
}
