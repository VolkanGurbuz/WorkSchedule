package dev.volkangurbuz.workschedule.controller;

import dev.volkangurbuz.workschedule.WorkScheduleApplication;
import dev.volkangurbuz.workschedule.model.ERole;
import dev.volkangurbuz.workschedule.model.dto.WorkerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    classes = WorkScheduleApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AuthControllerTest {

  private MockMvc mockMvc;

  @Autowired AuthController authController;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
  }

  @Test
  @DisplayName("get register page")
  void getRegisterPage() throws Exception {
    this.mockMvc.perform(get("/register/")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  @DisplayName("post register")
  void postRegisterPage() throws Exception {

    mockMvc
        .perform(
            post("/register/")
                .flashAttr(
                    "workerDTO", new WorkerDTO(1L, "dummyUser", "passwprd", ERole.ROLE_ADMIN)))
        .andExpect(status().isCreated());
  }
}
