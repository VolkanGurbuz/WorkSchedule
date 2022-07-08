package dev.volkangurbuz.workschedule.repositories;

import dev.volkangurbuz.workschedule.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {}
