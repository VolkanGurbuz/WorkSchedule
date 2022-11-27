package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.exceptions.EntityNotFoundException;
import dev.volkangurbuz.workschedule.exceptions.ScheduleFoundException;
import dev.volkangurbuz.workschedule.model.*;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final Logger logger = LogManager.getLogger(this.getClass().getName());
  final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    monthlyPlan.setCreateDate(eMonthYear);
    return scheduleRepository.save(monthlyPlan);
  }

  @Override
  public MonthlyPlan getMonthlyPlan(Date eMonthYear) {

    var monthlyPlan = scheduleRepository.findByCreateDate(dateFormat.format(eMonthYear));

    if (monthlyPlan.isEmpty()) {
      throw new EntityNotFoundException("Plan is not released yet!");
    }

    return monthlyPlan.get();
  }
}
