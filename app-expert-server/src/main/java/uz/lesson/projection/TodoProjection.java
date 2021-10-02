package uz.lesson.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.lesson.entity.Todo;

@Projection(name = "todo", types = {Todo.class})
public interface TodoProjection {

    Integer getId();

    String getName();

    boolean isCompleted();

}
