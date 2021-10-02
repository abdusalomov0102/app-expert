package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lesson.entity.Project;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
