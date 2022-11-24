package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.exceptions.ScheduleFoundException;
import dev.volkangurbuz.workschedule.model.*;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  final DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

  final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public MonthlyPlan createMonthlyPlan(Date eMonthYear) {

    if (scheduleRepository.isScheduleExists(dateFormat.format(eMonthYear))) {
      throw new ScheduleFoundException("Plan is already created!");
    }

    var monthlyPlan = new MonthlyPlan();
    return scheduleRepository.save(monthlyPlan);
  }
}
