package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.MonthlyPlan;

import java.text.ParseException;
import java.util.Date;

public interface ScheduleService {

  MonthlyPlan createMonthlyPlan(Date date) throws ParseException;
}
