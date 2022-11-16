package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.EMonthYear;
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
  public MonthlyPlan createMonthlyPlan() {
    var monthlyPlan = new MonthlyPlan();
    return scheduleRepository.save(monthlyPlan);
  }
}
