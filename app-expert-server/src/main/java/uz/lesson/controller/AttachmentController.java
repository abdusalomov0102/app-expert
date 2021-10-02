package uz.lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.lesson.service.AttachmentService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService; //@Autowired chaqirish uchun ishlatiladi

    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        return ResponseEntity.ok(attachmentService.uploadFile(request));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> dowloadFile(@PathVariable UUID id) {
        return attachmentService.getFile(id);
    }

}
