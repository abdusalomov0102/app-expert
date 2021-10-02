package uz.lesson.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.lesson.entity.Role;
import uz.lesson.entity.User;
import uz.lesson.entity.enums.PersonType;
import uz.lesson.repository.RoleRepository;
import uz.lesson.repository.UserRepository;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String modeInitial;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (modeInitial.equals("always")) {
            HashSet<Role> roles = new HashSet<>(roleRepository.findAll());

            userRepository.save(new User(
                    "Jaxongir",
                    "Abdusalomov",
                    "Xoldarali",
                    "+998997213466",
                    passwordEncoder.encode("Root_123"),
                    "123456789",
                    "jaxongir0102@gmail.com",
                    PersonType.JURIDICAL,
                    roles
            ));
        }
    }

}
