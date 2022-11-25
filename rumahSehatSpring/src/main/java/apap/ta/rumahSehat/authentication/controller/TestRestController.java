package apap.ta.rumahSehat.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TestRestController {

    @RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
    public ResponseEntity<String> firstPage() {
        System.out.println("A");
        return ResponseEntity.ok("Hello World");
    }
}
