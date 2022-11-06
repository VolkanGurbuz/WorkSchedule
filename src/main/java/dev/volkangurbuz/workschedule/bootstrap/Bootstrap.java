//package dev.volkangurbuz.workschedule.bootstrap;
//
//import dev.volkangurbuz.workschedule.config.BCryptPasswordEncoder;
//import dev.volkangurbuz.workschedule.model.Worker;
//import dev.volkangurbuz.workschedule.model.ERole;
//import dev.volkangurbuz.workschedule.repositories.WorkerRepository;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class Bootstrap implements CommandLineRunner {
//
//  final WorkerRepository workerRepository;
//  final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//  public Bootstrap(WorkerRepository workerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//    this.workerRepository = workerRepository;
//    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//  }
//
//  @Override
//  public void run(String... args) {
////    Worker worker =
////        new Worker(1L, "VolkanGurbuz", bCryptPasswordEncoder.encode("test"), ERole.ROLE_ADMIN);
////    workerRepository.save(worker);
//  }
//}
