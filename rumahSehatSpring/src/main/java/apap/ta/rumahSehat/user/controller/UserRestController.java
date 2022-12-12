package apap.ta.rumahSehat.user.controller;

import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.dto.PasienDTO;
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
    public ResponseEntity<?> registrasiPasien(@RequestBody PasienDTO pasienDTO,
                                            BindingResult bindingResult) {
        var pasienModel = new PasienModel();
        pasienModel.setRole(RoleEnum.Pasien);
        pasienModel.setUsername(pasienDTO.getUsername());
        pasienModel.setNama(pasienModel.getNama());
        pasienModel.setEmail(pasienDTO.getEmail());
        pasienModel.setPassword(pasienDTO.getPassword());
        pasienModel.setUmur(pasienDTO.getUmur());

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
    public ResponseEntity<?> getListDokter(Authentication authentication) {

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
