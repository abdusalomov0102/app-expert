package uz.lesson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.lesson.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AbsEntity {

    @ManyToOne
    private PayType payType;

    private double amount;

    @ManyToOne
    private Project project;

    private Timestamp payDate;

}
