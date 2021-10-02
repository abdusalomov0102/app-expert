package uz.lesson.payload;

import lombok.Data;
import uz.lesson.entity.enums.PersonType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ReqUser {

    // NotBlank toliq bolish probelni olmaydi
    @Pattern(regexp = "^[a-zA-Z]{3,20}$")
    private String firstName, lastName;

    private String middleName;

    // Pattern phoneNumber ga oxshagan fieldlarni tekshirish
    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Phone number must be 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "^(?:(?=.*?\\p{N})(?=.*[\\p{S}\\p{P} ])(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]{4,16}$",
            message = "Parol uzunligi 8-16 oralig'ida bo'lishi, parolda kamida bitta katta harf, son va belgi bo'lishi shart.")
    private String password;

    @Pattern(regexp = "^[0-9]{9}$", message = "TIN must be 9 digits")
    private String tin;  // inn yoki stir

    @Email(message = "Email emasku bu oka!")
    private String email;

}
