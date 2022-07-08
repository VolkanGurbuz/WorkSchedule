package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkerServiceImpl implements WorkerService {

  final WorkerRepository workerRepository;

  public WorkerServiceImpl(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  @Override
  public Worker createNewWorker(Worker worker) {
    return workerRepository.save(worker);
  }
}
