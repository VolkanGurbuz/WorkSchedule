package dev.volkangurbuz.workschedule.controller;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.services.WorkerServiceImpl;
import dev.volkangurbuz.workschedule.utilities.results.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@Controller
public class AuthController {

  private final WorkerServiceImpl workerService;
  private final PasswordEncoder encoder;

  public AuthController(WorkerServiceImpl workerService, PasswordEncoder encoder) {
    this.workerService = workerService;
    this.encoder = encoder;
  }

  /**
   * Any user can access this API - No Authentication required
   *
   * @param worker
   * @return
   */
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public String registerWorker(@ModelAttribute Worker worker, Model model) {

    Result result =
        workerService.register(
            new Worker(
                worker.getUsername(),
                encoder.encode(worker.getPassword()),
                worker.getWorkerType()));

    model.addAttribute("result_message", result.getMessage());

    return "register";
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/register")
  public String registerPage(@ModelAttribute Worker worker, Model model) {
    model.addAttribute("worker", worker);
    return "register";
  }
}
