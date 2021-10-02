package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lesson.entity.Role;
import uz.lesson.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);

}
