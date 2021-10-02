package uz.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.lesson.entity.PayType;
import uz.lesson.entity.Payment;
import uz.lesson.entity.Project;
import uz.lesson.entity.enums.ProjectStatus;
import uz.lesson.payload.ApiResponse;
import uz.lesson.payload.ReqPayment;
import uz.lesson.repository.PayTypeRepository;
import uz.lesson.repository.PaymentRepository;
import uz.lesson.repository.ProjectRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PayTypeRepository payTypeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Payment getOne(UUID id) {
        return paymentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bunday to`lov topilmadi!!!"));
    }

    public Payment addPayment(ReqPayment reqPayment) {
        Payment payment = new Payment();
        setPayment(reqPayment, payment);
        return paymentRepository.save(payment);
    }

    public ApiResponse updatePayment(UUID id, ReqPayment reqPayment) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Bunday to`liv topilmadi."));
        setPayment(reqPayment, payment);

        try {
            paymentRepository.save(payment);
            return new ApiResponse(false, "Malumot O`zgartirilmadi.");
        } catch (Exception e) {
            return new ApiResponse(true, "Malumot o`rgartildi");
        }
    }

    private void setPayment(ReqPayment reqPayment, Payment payment) {
        PayType payType = payTypeRepository.findById(reqPayment.getPayTypeId()).orElseThrow(() -> new
                ResourceNotFoundException("Bunday to`lov turi mavjud emas."));
        Project project = projectRepository.findById(reqPayment.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Bunday lyhangiz mavjud emas."));
        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);
        payment.setAmount(reqPayment.getAmount());
        payment.setPayType(payType);
        payment.setProject(project);
        payment.setPayDate(new Timestamp(new Date().getTime()));
    }

}
