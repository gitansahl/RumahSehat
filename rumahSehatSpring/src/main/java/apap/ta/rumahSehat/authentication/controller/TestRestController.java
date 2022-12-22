package apap.ta.rumahSehat.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @GetMapping(value = { "/hello" })
    public ResponseEntity<String> firstPage() {
        return ResponseEntity.ok("Hello World");
    }
}
