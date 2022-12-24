package dev.volkangurbuz.workschedule.repositories

import dev.volkangurbuz.workschedule.model.Reason
import org.springframework.data.jpa.repository.JpaRepository

interface ReasonRepository : JpaRepository<Reason, Long>