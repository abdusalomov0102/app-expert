package uz.lesson.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.lesson.entity.enums.ExpertizeType;
import uz.lesson.entity.enums.PersonType;
import uz.lesson.entity.enums.ProjectStatus;
import uz.lesson.entity.enums.ProjectType;
import uz.lesson.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Project extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ManyToOne
    private User user;

    private String name;

    private String projector;

    private String projectorTin;

    private String projectorPhoneNumber;

    private boolean seenAdmin;
    private boolean seenExpert;
    private boolean seenClient = true;

    @ManyToOne
    private User expert;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @Enumerated(EnumType.STRING)
    private ExpertizeType expertizeType;

    @Column(updatable = false)
    private Integer appNumber;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @OneToMany(mappedBy = "project") // mappedBy hususiyati o'ziga boglangan tabledagi fieldlarni o'zi bilan olib yuradi
    private List<ProjectChat> projectChats;


//    *******************************************************

    @OneToMany
    private List<Attachment> permissionOrganization;

    @OneToMany
    private List<Attachment> engineeringAndSearching;

    @OneToMany
    private List<Attachment> art;

    @OneToMany
    private List<Attachment> confirmedDraft;

    @OneToMany
    private List<Attachment> working;

    @OneToMany
    private List<Attachment> defectAct;

    @OneToMany
    private List<Attachment> taskProject;

//    *******************************************************

}
