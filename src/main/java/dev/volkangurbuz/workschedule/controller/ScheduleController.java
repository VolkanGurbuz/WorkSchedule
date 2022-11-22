package dev.volkangurbuz.workschedule.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import dev.volkangurbuz.workschedule.services.ScheduleServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class ScheduleController {

  private final ScheduleServiceImpl scheduleService;

  public ScheduleController(ScheduleServiceImpl scheduleService) {
    this.scheduleService = scheduleService;
  }

  @PostMapping("/createSchedule")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<MonthlyPlan> adminAccess(
      @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date eMonthYear) {

    var optionalMonthlyPlan = scheduleService.createMonthlyPlan(eMonthYear);

    return new ResponseEntity<>(optionalMonthlyPlan, HttpStatus.CREATED);
  }
}
