package dev.volkangurbuz.workschedule.repositories;

import dev.volkangurbuz.workschedule.model.Attempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttemptsRepository extends JpaRepository<Attempts, Integer> {
  Optional<Attempts> findAttemptsByUsername(String username);
}
