package dev.volkangurbuz.workschedule.repositories;

import dev.volkangurbuz.workschedule.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
  Optional<Worker> findWorkerByUsername(String username);
}
