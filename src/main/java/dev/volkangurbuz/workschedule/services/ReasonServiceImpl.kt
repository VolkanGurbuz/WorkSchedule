package dev.volkangurbuz.workschedule.services

import dev.volkangurbuz.workschedule.model.EReasonLevel
import dev.volkangurbuz.workschedule.model.Reason
import dev.volkangurbuz.workschedule.repositories.ReasonRepository
import dev.volkangurbuz.workschedule.repositories.WorkerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReasonServiceImpl(
    private val reasonRepository: ReasonRepository,
    private val workerRepository: WorkerRepository
) : ReasonService {
    override fun addReason(workerId: Long, date: Date, exceptionLevel: EReasonLevel) {
        val worker =
            workerRepository.findById(workerId)
        if (worker.isEmpty) throw NoSuchElementException("No found any worker with this id")

        val reason = Reason(worker.get(), date, exceptionLevel)
        reasonRepository.save(reason)
    }
}