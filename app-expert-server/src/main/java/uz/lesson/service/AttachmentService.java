package uz.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.lesson.entity.Attachment;
import uz.lesson.entity.AttachmentContent;
import uz.lesson.repository.AttachmentContentRepository;
import uz.lesson.repository.AttachmentRepository;

import java.io.IOException;
import java.util.*;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public List<UUID> uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        List<UUID> uuidList = new ArrayList<>();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            assert file != null;
            Attachment attachment = new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize()
            );
            Attachment saveAttachment = attachmentRepository.save(attachment); // nomi razmeri content type saqlandi xozi
            AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), attachment);
            attachmentContentRepository.save(attachmentContent);
            uuidList.add(saveAttachment.getId());
//  //1-usul
//            uuidList.add(attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize())))).getAttachment().getId();

//  //2-usul
//            AttachmentContent attachmentContent = attachmentContentRepository.save(new AttachmentContent(file.getBytes(),
//                    attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()))));
//            uuidList.add(attachmentContent.getAttachment().getId());
        }
        return uuidList;
    }

    public HttpEntity<?> getFile(UUID id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment\"" + attachment.getName() + "\"")
                    .body(attachmentContent.getContent());
        } else {
            return null;
        }
    }

}
