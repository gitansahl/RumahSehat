package apap.ta.rumahSehat.user.controller;

import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.model.RoleEnum;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/api/user")
@CrossOrigin("*")
@Slf4j
public class UserRestController {
    @Autowired
    PasienService pasienService;

    @Autowired
    DokterService dokterService;

    @PostMapping(value = "/registration")
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
            log.info(String.format("%s registered.", pasienModel.getUsername()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            Setting.response(HttpStatus.OK, "Registration Success.")
                    );

        } catch (Exception e) {
            log.info(String.format("%s %s", pasienModel.getUsername(), e.getMessage()));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.OK.value(), e.getMessage())
                    );
        }
    }

    @GetMapping(value = "/dokter/get")
    private ResponseEntity getListDokter(Authentication authentication) {

        log.info(String.format("%s request list dokter", authentication.getName()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        Setting.response(
                                HttpStatus.OK,
                                dokterService.findAll()
                        )
                );
    }
}
