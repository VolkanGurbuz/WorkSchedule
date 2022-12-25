package dev.volkangurbuz.workschedule.repositories

import dev.volkangurbuz.workschedule.model.MonthlyPlan
import dev.volkangurbuz.workschedule.model.Reason
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.YearMonth

interface ReasonRepository : JpaRepository<Reason, Long> {

    @Query(
        value = "select r from Reason r where DATE_FORMAT(r.exceptionDate,'%m-%d') = :date"
    )
    fun findReasonsByMonth(date: YearMonth): List<MonthlyPlan>

}