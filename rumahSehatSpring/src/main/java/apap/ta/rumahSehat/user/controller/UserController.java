package apap.ta.rumahSehat.user.controller;

<<<<<<< HEAD
=======
import apap.ta.rumahSehat.user.dto.ApotekerDTO;
import apap.ta.rumahSehat.user.dto.DokterDTO;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
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
<<<<<<< HEAD

import java.util.ArrayList;
=======
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

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

<<<<<<< HEAD
    @RequestMapping(value = "/pasien", method = RequestMethod.GET)
    private String manajemenPasien(Model model) {
=======
    @GetMapping(value = "/pasien")
    public String manajemenPasien(Model model) {
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
        model.addAttribute("listPasien", pasienService.findAll());

        return "user/viewall-pasien";
    }
<<<<<<< HEAD
    @RequestMapping(value = "/apoteker", method = RequestMethod.GET)
    private String manajemenApoteker(Model model) {
=======
    @GetMapping(value = "/apoteker")
    public String manajemenApoteker(Model model) {
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
        model.addAttribute("listApoteker", apotekerService.findAll());

        return "user/viewall-apoteker";
    }
<<<<<<< HEAD
    @RequestMapping(value = "/dokter", method = RequestMethod.GET)
    private String manajemenDokter(Model model) {
=======
    @GetMapping(value = "/dokter")
    public String manajemenDokter(Model model) {
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
        model.addAttribute("listDokter", dokterService.findAll());

        return "user/viewall-dokter";
    }

    @GetMapping(value = "/apoteker/add")
<<<<<<< HEAD
    private String formAddApoteker(Model model) {
        ApotekerModel apotekerModel = new ApotekerModel();
        apotekerModel.setRole(RoleEnum.Apoteker);

        model.addAttribute("apoteker", new ApotekerModel());
=======
    public String formAddApoteker(Model model) {

        model.addAttribute("apoteker", new ApotekerDTO());
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

        return "user/form-add-apoteker";
    }

    @PostMapping(value = "/apoteker/add")
<<<<<<< HEAD
    private String addApotekerSubmit(@ModelAttribute ApotekerModel apotekerModel,
                                     BindingResult result,
                                     RedirectAttributes redirectAttrs) {
        apotekerModel.setRole(RoleEnum.Apoteker);
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/user/apoteker/add";
        }
=======
    public String addApotekerSubmit(@ModelAttribute ApotekerDTO apotekerDTO,
                                     RedirectAttributes redirectAttrs) {
        var apotekerModel = new ApotekerModel();
        apotekerModel.setRole(RoleEnum.Apoteker);
        apotekerModel.setPassword(apotekerDTO.getPassword());
        apotekerModel.setNama(apotekerDTO.getNama());
        apotekerModel.setUsername(apotekerDTO.getUsername());
        apotekerModel.setEmail(apotekerDTO.getEmail());

>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

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
<<<<<<< HEAD
    private String formAddDokter(Model model) {
        DokterModel dokterModel = new DokterModel();
        dokterModel.setRole(RoleEnum.Dokter);

        model.addAttribute("dokter", new DokterModel());
=======
    public String formAddDokter(Model model) {

        model.addAttribute("dokter", new DokterDTO());
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

        return "user/form-add-dokter";
    }

    @PostMapping(value = "/dokter/add")
<<<<<<< HEAD
    private String addDokterSubmit(@ModelAttribute DokterModel dokterModel,
                                   BindingResult result,
                                   RedirectAttributes redirectAttrs) {
        dokterModel.setRole(RoleEnum.Dokter);
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/user/dokter/add";
        }
=======
    public String addDokterSubmit(@ModelAttribute DokterDTO dokterDTO,
                                   BindingResult result,
                                   RedirectAttributes redirectAttrs) {
        var dokterModel = new DokterModel();
        dokterModel.setRole(RoleEnum.Dokter);
        dokterModel.setUsername(dokterDTO.getUsername());
        dokterModel.setNama(dokterDTO.getNama());
        dokterModel.setEmail(dokterDTO.getEmail());
        dokterModel.setPassword(dokterDTO.getPassword());
        dokterModel.setTarif(dokterDTO.getTarif());
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

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
