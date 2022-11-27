package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.exceptions.EntityNotFoundException;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import dev.volkangurbuz.workschedule.utilities.results.ErrorResult;
import dev.volkangurbuz.workschedule.utilities.results.Messages;
import dev.volkangurbuz.workschedule.utilities.results.Result;
import dev.volkangurbuz.workschedule.utilities.results.SuccessResult;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkerServiceImpl implements WorkerService, UserDetailsService {

  final WorkerRepository workerRepository;

  public WorkerServiceImpl(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  @Override
  @Transactional
  public WorkerDetailsImpl loadUserByUsername(String username) {
    var worker =
        workerRepository
            .findWorkerByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("no any user name"));
    return WorkerDetailsImpl.build(worker);
  }

  @Override
  public Result register(Worker worker) {
    try {
      workerRepository.save(worker);
      return new SuccessResult(true, Messages.WORKER_ADDED);
    } catch (Exception e) {
      return new ErrorResult(true, Messages.ERROR_MESSAGE);
    }
  }
}
