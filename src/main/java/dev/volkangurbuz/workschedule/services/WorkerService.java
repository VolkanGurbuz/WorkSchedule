package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.utilities.results.Result;

public interface WorkerService {

  WorkerDetailsImpl loadUserByUsername(String username);

  Result register(Worker worker);
}
