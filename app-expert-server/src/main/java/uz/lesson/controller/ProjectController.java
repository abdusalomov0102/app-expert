package uz.lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lesson.entity.Project;
import uz.lesson.entity.User;
import uz.lesson.repository.ProjectRepository;
import uz.lesson.service.ProjectService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOnePayment(@PathVariable UUID id) {
        User user = new User();
        Project project = projectService.getOne(user, id);
        return ResponseEntity.status(project.getId() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(project);
    }

}
