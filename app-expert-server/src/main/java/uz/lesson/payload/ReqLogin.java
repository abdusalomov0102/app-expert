package uz.lesson.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqLogin {

    @NotBlank
    private String username;

    @Pattern(regexp = "^(?:(?=.*?\\p{N})(?=.*[\\p{S}\\p{P} ])(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]{4,16}$",
            message = "Parol uzunligi 8-16 oralig'ida bo'lishi, parolda kamida bitta katta harf, son va belgi bo'lishi shart.")
    private String password;

}
