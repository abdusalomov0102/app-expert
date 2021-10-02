package uz.lesson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.lesson.entity.enums.PersonType;
import uz.lesson.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String middleName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(length = 9, unique = true, nullable = false)
    private String tin;  //inn yoki stir

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public User(String firstName, String lastName,
                String middleName, String phoneNumber,
                String password, String tin,
                String email, PersonType personType,
                Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.tin = tin;
        this.email = email;
        this.personType = personType;
        this.roles = roles;
    }

    //    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//
//    Spring Security majburiy metodlari
//    UserDelails ning majburiy metodlari
//
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;       // sistemaga kirish muddati
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;        // sistemaga kirish blocklanganmi
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;             // attemplarini o'chirish
    }

    @Override
    public boolean isEnabled() {
        return enabled;                // sistemaga kirish user uchun
    }

//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//
//    Spring Security majburiy metodlari
//    UserDelails ning majburiy metodlari
//
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
