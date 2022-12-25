package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.exceptions.EntityNotFoundException;
import dev.volkangurbuz.workschedule.exceptions.ScheduleFoundException;
import dev.volkangurbuz.workschedule.model.*;
import dev.volkangurbuz.workschedule.repositories.ReasonRepository;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final Logger logger = LogManager.getLogger(this.getClass().getName());
  final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  final ScheduleRepository scheduleRepository;
  final WorkerRepository workerRepository;
  final ReasonRepository reasonRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository, WorkerRepository workerRepository, ReasonRepository reasonRepository) {
    this.scheduleRepository = scheduleRepository;
    this.workerRepository = workerRepository;
    this.reasonRepository = reasonRepository;
  }

  @Override
  public MonthlyPlan createMonthlyPlan(Date eMonthYear) {

    if (scheduleRepository.isScheduleExists(dateFormat.format(eMonthYear))) {
      throw new ScheduleFoundException("Plan is already created!");
    }

    var reasonList = reasonRepository.findReasonsByMonth(YearMonth.from(eMonthYear.toInstant()));



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

  private MonthlyPlan prepareMonthlyPlan(Date date,  List<Reason> reasonList){

    var workerList = workerRepository.findAll();

    prepareShifts(date, reasonList,workerList);

    return null;
  }

  private void prepareShifts(Date date, List<Reason> reasonList, List<Worker> workerList) {
    /*
    TODO
     */

  }

}
