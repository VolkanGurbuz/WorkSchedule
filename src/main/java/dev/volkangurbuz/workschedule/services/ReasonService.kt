package dev.volkangurbuz.workschedule.services

import dev.volkangurbuz.workschedule.model.EReasonLevel
import java.util.Date

interface ReasonService {
    fun addReason(workerId: Long, date: Date, exceptionLevel: EReasonLevel)
}