package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.lesson.entity.PayType;

@RepositoryRestResource(path = "payType")
public interface PayTypeRepository extends JpaRepository<PayType, Integer> {
}
