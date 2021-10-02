package uz.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.lesson.entity.Project;
import uz.lesson.entity.User;
import uz.lesson.repository.ProjectRepository;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project getOne(User user, UUID id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday to`luv topilmadi"));
        if (user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ADMIN_ROLE"))) {
            project.setSeenAdmin(true);
        } else {
            if (user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("EXPERT_ROLE"))) {
                project.setSeenExpert(true);
            }
        }
        return project;
    }

}
