package uz.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lesson.entity.Attachment;
import uz.lesson.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachment(Attachment attachment);

}
