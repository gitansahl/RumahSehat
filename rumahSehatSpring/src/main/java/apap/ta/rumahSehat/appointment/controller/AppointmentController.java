package apap.ta.rumahSehat.appointment.controller;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.tagihan.TagihanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String listAppointment(Model model) {
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        model.addAttribute("listAppointment", listAppointment);
        return "appointment/viewall-appointment";
    }

    @GetMapping("/appointment/view/{kodeAppointment}" )
    public String viewDetailAppointmentPage(@PathVariable String kodeAppointment, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKodeAppointment(kodeAppointment);
        model.addAttribute("appointment", appointment);
        if (appointment.getResep() != null) {
            model.addAttribute("idResep", appointment.getResep().getIdResep());
        }
        return "appointment/view-appointment";
    }

    @PostMapping(value = "/appointment/view/{kodeAppointment}", params = {"ubahStatus"})
    private String updateIsDone(
            @PathVariable String kodeAppointment, Model model
    ) {
        AppointmentModel appointment = appointmentService.getAppointmentByKodeAppointment(kodeAppointment);
        if (appointment.getIsDone() == false && appointment.getResep().getIsDone() == true && appointment.getResep().getConfirmer() != null) {
            appointment.setIsDone(true);
//            TagihanModel tagihan = new TagihanModel();
            appointmentService.addAppointment(appointment);
        }
        model.addAttribute("appointment", appointment);
        if (appointment.getResep() != null) {
            model.addAttribute("idResep", appointment.getResep().getIdResep());
        }
        return "appointment/view-appointment";
    }


//    @GetMapping("appointment/add")
//    public String addAppointmentFormPage(Model model) {
//        AppointmentModel appointment = new AppointmentModel();
//
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("listDokter", appointment.getDokter());
////        model.addAttribute("listTugasExisting", tugasService.getListTugas());
//        return "form-add-appointment";
//    }
//
//    @PostMapping(value="/appointment/add", params = {"save"})
//    public String addAppointmentSubmitPage(@ModelAttribute AppointmentModel appointment, Model model) {
////        if (presensi.getListTugas() != null){
////            for (TugasModel tugas : presensi.getListTugas()){
////                TugasModel tm = tugasService.getTugasById(tugas.getIdTugas());
////                tugas.setPresensi(presensi);
//////                tugas.setNama();
////            }
////        }
////        if (presensiService.onTime(presensi.getWaktuMasuk())) {
////            presensi.setStatus(1);
////        }
////        else {
////            presensi.setStatus(0);
////        }
//
//        appointmentService.addAppointment(appointment);
//        model.addAttribute("appointment", appointment);
////        model.addAttribute("listKaryawan", karyawanService.getListKaryawan());
////        model.addAttribute("listTugasExisting", tugasService.getListTugas());
//        return "add-appointment";
//    }
}