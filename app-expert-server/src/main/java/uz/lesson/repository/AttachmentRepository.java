package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lesson.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
