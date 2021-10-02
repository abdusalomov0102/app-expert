package uz.lesson.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import uz.lesson.entity.enums.RoleName;

import javax.persistence.*;

@Entity
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }

}
