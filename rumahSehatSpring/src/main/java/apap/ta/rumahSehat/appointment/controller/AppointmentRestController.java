package apap.ta.rumahSehat.appointment.controller;

import apap.ta.rumahSehat.appointment.model.AppointmentDTO;
import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/appointment")
@Slf4j
public class AppointmentRestController {
    @Autowired
    PasienService pasienService;

    @Autowired
    DokterService dokterService;

    @Autowired
    AppointmentService appointmentService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointmentDTO,
                                          Authentication authentication) {
        var pasienModel = pasienService.findPasienByUsername(authentication.getName());

        var appointmentModel = new AppointmentModel();
        appointmentModel.setDokter(dokterService.findDokterByUsername(appointmentDTO.getUsernameDokter()));
        appointmentModel.setPasien(pasienModel);
        appointmentModel.setIsDone(false);
        appointmentModel.setWaktuAwal(appointmentDTO.getWaktuAwal());

        log.info(String.format("%s request to add appointment", authentication.getName()));

        if (!appointmentService.isValid(appointmentModel)) {
            log.info(String.format("%s requested schedule not available", authentication.getName()));

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Setting.response(
                            HttpStatus.BAD_REQUEST.value(),
                            "Schedule not available"
                    )
            );
        }

        appointmentService.addAppointment(appointmentModel);
        appointmentModel.setKodeAppointment("APT-" + appointmentModel.getIdAppointment());
        appointmentService.addAppointment(appointmentModel);

        log.info(String.format("%s requested appointment successfully added.", authentication.getName()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK.value(),
                        "Appointment created successfully"
                ));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAppointment(Authentication authentication) {
        log.info(String.format("%s request get list appointment.", authentication.getName()));

        var pasienModel = pasienService.findPasienByUsername(authentication.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        appointmentService.getPasienListAppointment(pasienModel.getId())
                ));
    }
}
