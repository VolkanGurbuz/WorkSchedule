package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.EMonthYear;
import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import dev.volkangurbuz.workschedule.model.Worker;

import java.util.Optional;

public interface ScheduleService {

  MonthlyPlan createMonthlyPlan(EMonthYear eMonthYear);
}
