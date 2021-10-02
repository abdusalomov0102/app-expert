package uz.lesson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.lesson.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectChat extends AbsEntity {

    @ManyToOne
    private Project project;

    private String request;

    @OneToOne
    private Attachment byExpert;

    private boolean responded;
    private String response;

    @OneToOne
    private Attachment byClient;

}
