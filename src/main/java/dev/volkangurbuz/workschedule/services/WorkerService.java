package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import org.springframework.security.core.userdetails.UserDetails;

public interface WorkerService {

  UserDetails loadUserByUsername(String username);
}
