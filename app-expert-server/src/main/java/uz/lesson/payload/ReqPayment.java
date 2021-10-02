package uz.lesson.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPayment {

    @NotNull
    private Integer payTypeId;

    private double amount;

    @NotNull
    private UUID projectId;

    private Timestamp payDate;

}
