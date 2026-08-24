package iga.dogservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/version")
public class VersionController {
    @Value("${app.version}")
    private String version;

    @GetMapping
    public Map<String, String> getVersion() {
        return Map.of(
                "service", "dog-service",
                "version", version,
                "test", "push deployment test second run"
        );
    }
}
