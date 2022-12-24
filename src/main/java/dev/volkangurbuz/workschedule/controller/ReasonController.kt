package dev.volkangurbuz.workschedule.controller

import dev.volkangurbuz.workschedule.model.EReasonLevel
import dev.volkangurbuz.workschedule.services.ReasonServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class ReasonController(private val reasonServiceImpl: ReasonServiceImpl) {

    private val log = LoggerFactory.getLogger(ReasonController::class.java)

    @PostMapping("/add-reason")
    fun addReason(@RequestBody reasonRequest: ReasonRequestDTO): ResponseEntity<Void> {
        return try {
            reasonServiceImpl.addReason(reasonRequest.workerId, reasonRequest.date, reasonRequest.eReasonLevel)
            ResponseEntity.ok().build()
        } catch (e: java.lang.Exception) {
            log.error("Unexpected error while updating billing address.", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

data class ReasonRequestDTO(
    val workerId: Long,
    val date: Date,
    val eReasonLevel: EReasonLevel
)
