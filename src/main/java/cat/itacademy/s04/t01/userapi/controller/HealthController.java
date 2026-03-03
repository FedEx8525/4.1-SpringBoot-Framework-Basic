package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.dto.HealthStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public HealthStatus printOk() {
        return new HealthStatus("OK");
    }
}
