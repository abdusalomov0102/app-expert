package uz.lesson.payload;

import lombok.Data;
import uz.lesson.entity.enums.ExpertizeType;
import uz.lesson.entity.enums.PersonType;
import uz.lesson.entity.enums.ProjectStatus;
import uz.lesson.entity.enums.ProjectType;

import java.util.List;
import java.util.UUID;

@Data
public class ReqProject {

    private PersonType personType;

    private UUID userId;

    private String name;

    private String projector;

    private String projectorTin;

    private String projectorPhoneNumber;

    private ProjectType projectType;

    private ExpertizeType expertizeType;

    private Integer appNumber;

    private ProjectStatus projectStatus;

    private List<UUID> permissionOrganization;

    private List<UUID> engineeringAndSearching;

    private List<UUID> art;

    private List<UUID> confirmedDraft;

    private List<UUID> working;

    private List<UUID> defectAct;

    private List<UUID> taskProject;

}
