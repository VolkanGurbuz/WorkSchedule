package dev.volkangurbuz.workschedule.repositories;
import java.util.Optional;

import dev.volkangurbuz.workschedule.model.ERole;
import dev.volkangurbuz.workschedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}