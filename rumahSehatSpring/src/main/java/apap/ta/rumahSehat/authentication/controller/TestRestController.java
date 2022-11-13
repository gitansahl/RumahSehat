package apap.ta.rumahSehat.authentication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Hello World";
    }
}
