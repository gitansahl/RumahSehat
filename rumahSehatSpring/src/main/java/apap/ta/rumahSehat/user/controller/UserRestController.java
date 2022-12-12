package apap.ta.rumahSehat.user.controller;

import apap.ta.rumahSehat.authentication.setting.Setting;
<<<<<<< HEAD
=======
import apap.ta.rumahSehat.user.dto.PasienDTO;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.model.RoleEnum;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
=======

import javax.validation.Valid;
import java.util.ArrayList;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

@Controller
@RequestMapping("/api/user")
@CrossOrigin("*")
<<<<<<< HEAD
=======
@Slf4j
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
public class UserRestController {
    @Autowired
    PasienService pasienService;

    @Autowired
    DokterService dokterService;

    @PostMapping(value = "/registration")
<<<<<<< HEAD
    private ResponseEntity registrasiPasien(@Valid @RequestBody PasienModel pasienModel,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        pasienModel.setRole(RoleEnum.Pasien);
        try {
            pasienService.addPasien(pasienModel);
=======
    public ResponseEntity<?> registrasiPasien(@RequestBody PasienDTO pasienDTO,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.BAD_REQUEST.value(), "Error occurred!")
                    );
        }

        try {
            pasienService.addPasien(pasienDTO);
            log.info(String.format("%s registered.", pasienDTO.getUsername()));
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            Setting.response(HttpStatus.OK, "Registration Success.")
                    );

        } catch (Exception e) {
<<<<<<< HEAD
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.OK.value(), e.getMessage())
=======
            log.info(String.format("%s %s", pasienDTO.getUsername(), e.getMessage()));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.BAD_REQUEST.value(), e.getMessage())
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
                    );
        }
    }

    @GetMapping(value = "/dokter/get")
<<<<<<< HEAD
    private ResponseEntity getListDokter() {
=======
    public ResponseEntity<?> getListDokter(Authentication authentication) {

        log.info(String.format("%s request list dokter", authentication.getName()));

>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        Setting.response(
                                HttpStatus.OK,
                                dokterService.findAll()
                        )
                );
    }
<<<<<<< HEAD

=======
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
}
