package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public MonthlyPlan createMonthlyPlan(MonthlyPlan monthlyPlan) {

    return scheduleRepository.save(monthlyPlan);
  }
}
