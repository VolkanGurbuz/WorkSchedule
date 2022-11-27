package dev.volkangurbuz.workschedule.repositories;

import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<MonthlyPlan, Long> {

  @Query(
      value =
          "select count(mp.id) >0 from MonthlyPlan mp where DATE_FORMAT(mp.createDate,'%Y-%m-%d') = :date")
  boolean isScheduleExists(String date);

  @Query(
      value = "select mp from MonthlyPlan mp where DATE_FORMAT(mp.createDate,'%Y-%m-%d') = :date")
  Optional<MonthlyPlan> findByCreateDate(String date);
}
