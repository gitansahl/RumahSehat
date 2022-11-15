package apap.ta.rumahSehat.user.controller;

import apap.ta.rumahSehat.user.service.ApotekerService;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
import apap.ta.rumahSehat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasienService pasienService;

    @Autowired
    ApotekerService apotekerService;

    @Autowired
    DokterService dokterService;

    @RequestMapping(value = "/manajemen", method = RequestMethod.GET)
    private String manajemenUser(Model model) {

        model.addAttribute("listPasien", pasienService.findAll());
        model.addAttribute("listApoteker", apotekerService.findAll());
        model.addAttribute("listDokter", dokterService.findAll());

        return "user/manajemen";
    }
}
