package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lesson.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmailEqualsIgnoreCase(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByTin(String tin);

    Optional<User> findByPhoneNumberOrEmail(String phoneNumber, String email);

}
