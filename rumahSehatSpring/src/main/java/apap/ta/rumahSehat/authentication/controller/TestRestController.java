package apap.ta.rumahSehat.authentication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TestRestController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }
}
