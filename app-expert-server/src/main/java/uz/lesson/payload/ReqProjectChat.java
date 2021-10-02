package uz.lesson.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqProjectChat {

    private UUID projectId;

    private String request;

    private UUID byExpertAttachmentId;

    private boolean responded;

    private String response;

    private UUID byClientAttachmentId;

}
