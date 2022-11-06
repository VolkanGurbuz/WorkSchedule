package dev.volkangurbuz.workschedule.services;

import dev.volkangurbuz.workschedule.model.Worker;
import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
import dev.volkangurbuz.workschedule.utilities.results.ErrorResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

class WorkerServiceTest {

  @InjectMocks WorkerServiceImpl workerService;

  @Mock WorkerRepository workerRepository;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findWorkerByUsername() {
    when(workerRepository.findWorkerByUsername("volkan"))
        .thenReturn(Optional.of(new Worker("volkan", "test@gmail.com","test")));

    var testWorker = workerService.loadUserByUsername("volkan");

    Assertions.assertEquals("volkan", testWorker.getUsername());
  }

  @Test
  void findWorkerByUsernameFalse() {
    when(workerRepository.findWorkerByUsername("volkan"))
        .thenReturn(Optional.of(new Worker("volkan", "test@gmail.com", "test" )));

    var testWorker = workerService.loadUserByUsername("volkan");

    Assertions.assertNotEquals("volkanFalse", testWorker.getUsername());
  }

  @Test
  void registerService() {
    var worker = new Worker("volkan", "test@gmail.com", "test");
    when(workerRepository.save(worker)).thenReturn(worker);

    var testWorker = workerService.register(worker);

    Assertions.assertTrue(testWorker.isSuccess());
  }

  @Test
  void registerServiceFalse() {
    var worker = new Worker("volkan", "test@gmail.com", "test");
    when(workerService.register(worker)).thenReturn(new ErrorResult(false, "something happen"));

    var testWorker = workerService.register(worker);

    Assertions.assertFalse(testWorker.isSuccess());
  }
}
