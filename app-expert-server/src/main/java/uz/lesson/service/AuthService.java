package uz.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.lesson.entity.User;
import uz.lesson.entity.enums.PersonType;
import uz.lesson.entity.enums.RoleName;
import uz.lesson.payload.ApiResponse;
import uz.lesson.payload.ReqUser;
import uz.lesson.repository.RoleRepository;
import uz.lesson.repository.UserRepository;

import java.util.Collections;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse register(ReqUser reqUser) {
        if (!userRepository.existsByPhoneNumber(reqUser.getPhoneNumber())) {
            if (!userRepository.existsByEmailEqualsIgnoreCase(reqUser.getEmail())) {
                if (!userRepository.existsByTin(reqUser.getTin())) {
                    User user = new User(
                            reqUser.getFirstName(),
                            reqUser.getLastName(),
                            reqUser.getMiddleName(),
                            reqUser.getPhoneNumber(),
                            passwordEncoder.encode(reqUser.getPassword()),
                            reqUser.getTin(),
                            reqUser.getEmail(),
                            PersonType.PHYSICAL,
                            Collections.singleton(roleRepository.findByRoleName(RoleName.USER_ROLE))
                    );
                    userRepository.save(user);
                    return new ApiResponse(true, "Saqlandi!!!");
                }
                return new ApiResponse(false, "Tin mavjud!!!");
            }
            return new ApiResponse(false, "Email mavjud!!!");
        }
        return new ApiResponse(false, "Telefon nomer mavjud!!!");
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumberOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException(username));
//        Optional<User> user = userRepository.findByPhoneNumberOrEmail(username, username);
//        return user.get();
    }

    public String getKetmonName(User user) {
        return user.getFirstName() + ", "
                + ", " + user.getLastName()
                + ", " + user.getMiddleName();
    }

}
