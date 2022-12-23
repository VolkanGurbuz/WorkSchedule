package controller

import dev.volkangurbuz.workschedule.model.MonthlyPlan
import dev.volkangurbuz.workschedule.services.ScheduleServiceImpl
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.Date

@RestController
@CrossOrigin(origins = arrayOf("*"), maxAge = 3600)
@RequestMapping("/api")
class ScheduleController(private val scheduleService: ScheduleServiceImpl) {

    @PostMapping("/createSchedule")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun createPlan(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") eMonthYear: Date): ResponseEntity<MonthlyPlan> {
        return ResponseEntity.ok(scheduleService.createMonthlyPlan(eMonthYear))
    }

    @GetMapping("/getSchedule")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    fun getPlan(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") eMonthYear: Date
    ): ResponseEntity<MonthlyPlan> {
        return ResponseEntity.ok(scheduleService.getMonthlyPlan(eMonthYear))
    }

}