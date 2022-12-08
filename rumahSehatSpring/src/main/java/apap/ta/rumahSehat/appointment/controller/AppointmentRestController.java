package apap.ta.rumahSehat.appointment.controller;

import apap.ta.rumahSehat.appointment.model.AppointmentDTO;
import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.repository.PasienDb;
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/appointment")
public class AppointmentRestController {
    @Autowired
    PasienService pasienService;

    @Autowired
    DokterService dokterService;

    @Autowired
    AppointmentService appointmentService;

    @PostMapping(value = "/add")
    private ResponseEntity addAppointment(@RequestBody AppointmentDTO appointmentDTO,
                                          Authentication authentication) {
        PasienModel pasienModel = pasienService.findPasienByUsername(authentication.getName());



        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setDokter(dokterService.findDokterByUsername(appointmentDTO.getUsernameDokter()));
        appointmentModel.setPasien(pasienModel);
        appointmentModel.setIsDone(false);
        appointmentModel.setWaktuAwal(appointmentDTO.getWaktuAwal());

        if (!appointmentService.isValid(appointmentModel)) {
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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK.value(),
                        "Appointment created successfully"
                ));
    }

    @GetMapping("/get")
    private ResponseEntity getAppointment(Authentication authentication) {
        PasienModel pasienModel = pasienService.findPasienByUsername(authentication.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        appointmentService.getPasienListAppointment(pasienModel.getId())
                ));
    }
}
