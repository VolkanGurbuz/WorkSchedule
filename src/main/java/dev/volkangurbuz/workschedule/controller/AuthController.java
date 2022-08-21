package dev.volkangurbuz.workschedule.controller;

import dev.volkangurbuz.workschedule.model.ERole;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.model.dto.WorkerDTO;
import dev.volkangurbuz.workschedule.services.WorkerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@Controller
public class AuthController {

  private final WorkerServiceImpl workerService;
  private final PasswordEncoder encoder;
  private final ModelMapper modelMapper;

  public AuthController(
      WorkerServiceImpl workerService, PasswordEncoder encoder, ModelMapper modelMapper) {
    this.workerService = workerService;
    this.encoder = encoder;
    this.modelMapper = modelMapper;
  }

  /**
   * Any user can access this API - No Authentication required
   *
   * @param workerDTO, model
   * @return
   */
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public String registerWorker(@ModelAttribute WorkerDTO workerDTO, Model model) {
    workerDTO.setWorkerType(ERole.ROLE_USER);
    var workerMap = modelMapper.map(workerDTO, Worker.class);

    var result =
        workerService.register(
            new Worker(workerMap.getUsername(), encoder.encode(workerMap.getPassword())));

    model.addAttribute("result_message", result.getMessage());

    return "register";
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/register")
  public String registerPage(@ModelAttribute WorkerDTO worker, Model model) {
    model.addAttribute("worker", worker);
    return "register";
  }
}
