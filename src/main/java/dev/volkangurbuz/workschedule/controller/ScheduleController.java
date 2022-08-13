package dev.volkangurbuz.workschedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScheduleController {

  @GetMapping("index")
  public String showUploadForm() {
    return "templates/index.html";
  }
}
