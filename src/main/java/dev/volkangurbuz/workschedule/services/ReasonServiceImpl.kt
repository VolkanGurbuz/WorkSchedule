package dev.volkangurbuz.workschedule.services

import dev.volkangurbuz.workschedule.model.EReasonLevel
import dev.volkangurbuz.workschedule.model.Reason
import dev.volkangurbuz.workschedule.repositories.ReasonRepository
import dev.volkangurbuz.workschedule.repositories.WorkerRepository
import java.util.*

class ReasonServiceImpl(
    private val reasonRepository: ReasonRepository,
    private val workerRepository: WorkerRepository
) : ReasonService {
    override fun addReason(workerId: Long, date: Date, eReasonLevel: EReasonLevel) {
        val worker =
            workerRepository.findById(workerId)
        if (worker.isEmpty) throw NoSuchElementException("No found any worker with this id")

        val reason = Reason(worker.get(), eReasonLevel, date)
        reasonRepository.save(reason)
    }
}