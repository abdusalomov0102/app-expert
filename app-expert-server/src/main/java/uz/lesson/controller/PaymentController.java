package uz.lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.lesson.entity.Payment;
import uz.lesson.entity.User;
import uz.lesson.payload.ApiResponse;
import uz.lesson.payload.ReqPayment;
import uz.lesson.repository.PaymentRepository;
import uz.lesson.security.CurrentUser;
import uz.lesson.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    //    @PreAuthorize("hasRole('DIRECTOR_ROLE')")
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayment(@CurrentUser User user) {
        System.out.println(user);
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getOnePayment(@PathVariable UUID id) {
        Payment payment = paymentService.getOne(id);
        return ResponseEntity.status(payment.getId() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(payment);
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody ReqPayment reqPayment) {
        Payment payment = paymentService.addPayment(reqPayment);
        return ResponseEntity.status(payment.getId() == null ?
                HttpStatus.CONFLICT : HttpStatus.OK).body(payment);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updatePayment(@PathVariable UUID id, @RequestBody ReqPayment reqPayment) {
        ApiResponse apiResponse = paymentService.updatePayment(id, reqPayment);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        try {
            paymentRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "To`lov muvaffaqiyatli o`chirildi."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, "Nosozlik " +
                    "tufayli to`lov o`chirilmadi qaytadan uninib ko`ring!!!"));
        }
    }

}
