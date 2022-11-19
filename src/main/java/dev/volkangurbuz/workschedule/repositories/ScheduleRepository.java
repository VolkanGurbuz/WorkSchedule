package dev.volkangurbuz.workschedule.repositories;

import dev.volkangurbuz.workschedule.model.EMonthYear;
import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<MonthlyPlan, Long> {

  boolean existsByeMonthYear(EMonthYear eMonthYear);
}
