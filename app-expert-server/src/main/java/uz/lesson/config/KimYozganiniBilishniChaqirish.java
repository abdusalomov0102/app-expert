package uz.lesson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.lesson.entity.User;

import java.util.Optional;
import java.util.UUID;

//class name AuditConfig

@Configuration
@EnableJpaAuditing
public class KimYozganiniBilishniChaqirish {

    @Bean
    AuditorAware<UUID> kimniBilish() {
        return new KimYozganiniBilish();
    }

}

class KimYozganiniBilish implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication bu login qilib kirgan odamni malumotlarini olib berish
        if (!(authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal()))) {
            User user = (User) authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }

}
