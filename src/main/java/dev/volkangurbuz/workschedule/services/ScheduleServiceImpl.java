package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.*;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public Optional<MonthlyPlan> createMonthlyPlan(Worker worker, EMonthYear eMonthYear) {
    if (scheduleRepository.existsByeMonthYear(eMonthYear)) {
      return Optional.empty();
    }

    var monthlyPlan = new MonthlyPlan();
    return Optional.of(scheduleRepository.save(monthlyPlan));
  }
}
