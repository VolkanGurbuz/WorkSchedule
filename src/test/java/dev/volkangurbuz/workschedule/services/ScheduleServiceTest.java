package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.EMonthYear;
import dev.volkangurbuz.workschedule.model.MonthlyPlan;
import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ScheduleServiceTest {

  ScheduleService scheduleService;
  AutoCloseable closeable;
  @Mock ScheduleRepository scheduleRepository;

  @BeforeEach
  public void init() {
    closeable = MockitoAnnotations.openMocks(this);
    scheduleService = new ScheduleServiceImpl(scheduleRepository);
  }

  @AfterEach
  void closeService() throws Exception {
    closeable.close();
  }

  @Test
  void saveWorkPlan() {
    MonthlyPlan m = new MonthlyPlan();
    m.setName(EMonthYear.JULY);
    when(scheduleRepository.save(any(MonthlyPlan.class))).thenReturn(m);

    var testWorker = scheduleService.createMonthlyPlan();

    Assertions.assertEquals(EMonthYear.JULY, testWorker.getName());
  }
}
