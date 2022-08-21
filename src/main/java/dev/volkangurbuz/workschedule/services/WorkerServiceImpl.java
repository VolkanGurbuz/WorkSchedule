package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import dev.volkangurbuz.workschedule.utilities.results.ErrorResult;
import dev.volkangurbuz.workschedule.utilities.results.Messages;
import dev.volkangurbuz.workschedule.utilities.results.Result;
import dev.volkangurbuz.workschedule.utilities.results.SuccessResult;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkerServiceImpl implements WorkerService {

  final WorkerRepository workerRepository;

  public WorkerServiceImpl(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  @Override
  public Worker loadUserByUsername(String username) {
    var optionalWorker = workerRepository.findWorkerByUsername(username);
    return optionalWorker.orElse(null);
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
