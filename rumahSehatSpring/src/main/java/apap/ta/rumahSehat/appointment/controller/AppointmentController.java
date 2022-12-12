package apap.ta.rumahSehat.appointment.controller;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.tagihan.service.TagihanService;
import apap.ta.rumahSehat.user.model.AdminModel;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.service.AdminService;
import apap.ta.rumahSehat.user.service.DokterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("tagihanServiceImpl")
    @Autowired
    private TagihanService tagihanService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("adminServiceImpl")
    @Autowired
    private AdminService adminService;

    // @GetMapping("/appointment")
    // public String listAppointment(Model model) {
    //     List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
    //     model.addAttribute("listAppointment", listAppointment);
    //     return "appointment/viewall-appointment";
    // }

    @GetMapping("/appointment")
    public String viewAllAppointment(Model model) { // admin dan dokter only
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        DokterModel dokter = dokterService.findDokterByUsername(username);
        AdminModel admin = adminService.getAdminByUsername(username);

        List<AppointmentModel> listAppointment = new ArrayList<>();

        if (admin != null) {
            listAppointment = appointmentService.getListAppointment();
            listAppointment = (listAppointment == null) ? new ArrayList<>() : listAppointment; // kalau null diisi list kosong aja
            model.addAttribute("listAppointment", listAppointment);
            return "appointment/viewall-appointment";

        } else if (dokter != null) {
            // listAppointment = appointmentService.getListAppointmentByDokter(dokter);
            listAppointment = appointmentService.getListAppointment();
            listAppointment = (listAppointment == null) ? new ArrayList<>() : listAppointment; // kalau null diisi list kosong aja
            model.addAttribute("listAppointment", listAppointment);
            return "appointment/viewall-appointment";

        } else { // role nya gabener, tp harusnya nanti udh di handle websecurityconfig sih
            model.addAttribute("errorMessage", "Anda (" + username + ") tidak memiliki akses untuk membuka halaman ini (role salah).");
            return "error/400";
        }
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

    @PostMapping("/appointment/view/{kodeAppointment}")
    private String updateIsDone(@PathVariable String kodeAppointment, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKodeAppointment(kodeAppointment);
        ResepModel resep = appointment.getResep();
        if (resep == null || resep.getIsDone()) {
            model.addAttribute("appointment", appointment);
            
            TagihanModel tagihan = tagihanService.addTagihanByDokter(appointment);
            appointment.setTagihan(tagihan);
            appointmentService.finishAppointment(appointment);
        }
        if (appointment.getIsDone() == false && appointment.getResep().getIsDone() == true && appointment.getResep().getConfirmer() != null) {
            appointment.setIsDone(true);
            appointmentService.addAppointment(appointment);
        }
        model.addAttribute("appointment", appointment);
        // if (appointment.getResep() != null) {
        //     model.addAttribute("idResep", appointment.getResep().getIdResep());
        // }
        return "appointment/view-appointment";
    }
}
