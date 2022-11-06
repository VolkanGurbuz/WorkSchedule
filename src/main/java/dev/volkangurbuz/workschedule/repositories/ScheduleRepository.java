package dev.volkangurbuz.workschedule.repositories;

 import dev.volkangurbuz.workschedule.model.MonthlyPlan;
 import org.springframework.data.jpa.repository.JpaRepository;

 public interface ScheduleRepository extends JpaRepository<MonthlyPlan, Long> {}
