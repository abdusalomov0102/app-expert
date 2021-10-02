package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.lesson.entity.Todo;
import uz.lesson.projection.TodoProjection;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RepositoryRestResource(path = "todo", excerptProjection = TodoProjection.class)
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}