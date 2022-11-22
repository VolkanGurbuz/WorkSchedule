package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.MonthlyPlan;

import java.util.Date;

public interface ScheduleService {

  MonthlyPlan createMonthlyPlan(Date date);
}
