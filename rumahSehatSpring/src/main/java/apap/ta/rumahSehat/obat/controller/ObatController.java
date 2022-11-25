package apap.ta.rumahSehat.obat.controller;

import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ObatController {

    @Autowired
    private ObatService obatService;

    @GetMapping("/obat")
    private String viewallObat(Authentication authentication, Model model) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))
                || authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Apoteker"))) {
            List<ObatModel> listObat = obatService.getListObat();
            model.addAttribute("listObat", listObat);
            return "obat/viewall-obat";
        } else {
            return "error/403";
        }
    }
}
