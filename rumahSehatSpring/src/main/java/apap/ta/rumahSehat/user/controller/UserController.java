package apap.ta.rumahSehat.user.controller;

import apap.ta.rumahSehat.user.dto.ApotekerDTO;
import apap.ta.rumahSehat.user.dto.DokterDTO;
import apap.ta.rumahSehat.user.model.ApotekerModel;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.model.RoleEnum;
import apap.ta.rumahSehat.user.service.ApotekerService;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
import apap.ta.rumahSehat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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

    @RequestMapping(value = "/pasien", method = RequestMethod.GET)
    private String manajemenPasien(Model model) {
        model.addAttribute("listPasien", pasienService.findAll());

        return "user/viewall-pasien";
    }
    @RequestMapping(value = "/apoteker", method = RequestMethod.GET)
    private String manajemenApoteker(Model model) {
        model.addAttribute("listApoteker", apotekerService.findAll());

        return "user/viewall-apoteker";
    }
    @RequestMapping(value = "/dokter", method = RequestMethod.GET)
    private String manajemenDokter(Model model) {
        model.addAttribute("listDokter", dokterService.findAll());

        return "user/viewall-dokter";
    }

    @GetMapping(value = "/apoteker/add")
    private String formAddApoteker(Model model) {

        model.addAttribute("apoteker", new ApotekerDTO());

        return "user/form-add-apoteker";
    }

    @PostMapping(value = "/apoteker/add")
    private String addApotekerSubmit(@ModelAttribute ApotekerDTO apotekerDTO,
                                     RedirectAttributes redirectAttrs) {
        ApotekerModel apotekerModel = new ApotekerModel();
        apotekerModel.setRole(RoleEnum.Apoteker);
        apotekerModel.setPassword(apotekerDTO.getPasssword());
        apotekerModel.setNama(apotekerDTO.getNama());
        apotekerModel.setUsername(apotekerDTO.getUsername());
        apotekerModel.setEmail(apotekerDTO.getEmail());


        try {
            apotekerService.addApoteker(apotekerModel);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/apoteker/add";
        }

        redirectAttrs.addFlashAttribute("success",
                String.format("Apoteker dengan username %s berhasil ditambahkan", apotekerModel.getUsername()));
        return "redirect:/user/apoteker";
    }

    @GetMapping(value = "/dokter/add")
    private String formAddDokter(Model model) {

        model.addAttribute("dokter", new DokterDTO());

        return "user/form-add-dokter";
    }

    @PostMapping(value = "/dokter/add")
    private String addDokterSubmit(@ModelAttribute DokterDTO dokterDTO,
                                   BindingResult result,
                                   RedirectAttributes redirectAttrs) {
        DokterModel dokterModel = new DokterModel();
        dokterModel.setRole(RoleEnum.Dokter);
        dokterModel.setUsername(dokterDTO.getUsername());
        dokterModel.setNama(dokterDTO.getNama());
        dokterModel.setEmail(dokterDTO.getEmail());
        dokterModel.setPassword(dokterDTO.getPasssword());
        dokterModel.setTarif(dokterDTO.getTarif());

        try {
            dokterService.addDokter(dokterModel);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/dokter/add";
        }

        redirectAttrs.addFlashAttribute("success",
                String.format("Dokter dengan username %s berhasil ditambahkan.", dokterModel.getUsername()));
        return "redirect:/user/dokter";
    }
}
