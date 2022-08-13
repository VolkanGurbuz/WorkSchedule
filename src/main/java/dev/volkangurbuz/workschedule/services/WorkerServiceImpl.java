package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkerServiceImpl implements WorkerService {

  final WorkerRepository workerRepository;

  public WorkerServiceImpl(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  public void createUser(UserDetails user) {
    workerRepository.save((Worker) user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    //    return workerRepository
    //        .findWorkerByUsername(username)
    //        .orElseThrow(() -> new UsernameNotFoundException("user not present"));
    return null;
  }
}
