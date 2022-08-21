package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.utilities.results.Result;
import org.springframework.security.core.userdetails.UserDetails;

public interface WorkerService {

  Worker loadUserByUsername(String username);

  Result register(Worker worker);
}
