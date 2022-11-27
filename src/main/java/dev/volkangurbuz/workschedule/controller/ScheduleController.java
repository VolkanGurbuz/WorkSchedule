package dev.volkangurbuz.workschedule.controller;

import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import dev.volkangurbuz.workschedule.services.ScheduleServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ScheduleController {

  private final ScheduleServiceImpl scheduleService;

  public ScheduleController(ScheduleServiceImpl scheduleService) {
    this.scheduleService = scheduleService;
  }

  @PostMapping("/createSchedule")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<MonthlyPlan> createPlan(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date eMonthYear) {

    var monthlyPlan = scheduleService.createMonthlyPlan(eMonthYear);

    return new ResponseEntity<>(monthlyPlan, HttpStatus.CREATED);
  }

  @PostMapping("/getSchedule")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<MonthlyPlan> getPlan(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date eMonthYear) {

    var monthlyPlan = scheduleService.getMonthlyPlan(eMonthYear);

    return new ResponseEntity<>(monthlyPlan, HttpStatus.OK);
  }
}
