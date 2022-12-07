package apap.ta.rumahSehat.resep.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.service.ObatService;
import apap.ta.rumahSehat.resep.model.JumlahModel;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.service.JumlahService;
import apap.ta.rumahSehat.resep.service.ResepService;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResepController {
  @Qualifier("resepServiceImpl")
  @Autowired
  private ResepService resepService;

  @Qualifier("obatServiceImpl")
  @Autowired
  private ObatService obatService;

  @Qualifier("jumlahServiceImpl")
  @Autowired
  private JumlahService jumlahService;

  @Qualifier("appointmentServiceImpl")
  @Autowired
  private AppointmentService appointmentService;

  @GetMapping("/resep")
  public String listResep(Model model) {
    List<ResepModel> listResep = resepService.getListResep();
    model.addAttribute("listResep", listResep);
    return "resep/viewall-resep";
  }

  @GetMapping("/resep/tambah/{idAppointment}")
  public String addResepFormPage(Model model, @PathVariable Long idAppointment) {
    ResepModel resep = new ResepModel();
    resep.setAppointment(appointmentService.getAppointmentByIdAppointment(idAppointment));
    resep.setIsDone(false);
    
    List<ObatModel> listObat = obatService.getListObat();
    JumlahModel jumlah = new JumlahModel();
    ObatModel obat = new ObatModel();

    jumlah.setObat(obat);
    List<JumlahModel> listJumlah = new ArrayList<>();
    resep.setListJumlah(listJumlah);
    resep.getListJumlah().add(jumlah);

    model.addAttribute("resep", resep);
    model.addAttribute("listObatExisting", listObat);
    
    return "resep/form-add-resep";
  }

  @PostMapping("/resep/tambah")
  public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
    for (JumlahModel jumlah : resep.getListJumlah()) {
      jumlah.setResep(resep);

      ObatModel obat = jumlah.getObat();
      ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());
      if (obatDb.getStok() < jumlah.getKuantitas()) {
          model.addAttribute("resep", resep);
          return "resep/cant-add-resep";
      } else {
          obatDb.setStok(obatDb.getStok() - jumlah.getKuantitas());
          jumlah.setObat(obatDb);
      }
    }

    LocalDateTime now = LocalDateTime.now();
    resep.setIsDone(false);
    resep.setCreatedAt(now);
    resep.setAppointment(appointmentService.getAppointmentByIdAppointment(resep.getAppointment().getIdAppointment()));

    resepService.addResep(resep);
    for (JumlahModel jumlahjumlah : resep.getListJumlah()) {
      ObatModel obat = jumlahjumlah.getObat();
      jumlahService.addJumlah(jumlahjumlah);
      obatService.updateStok(obat);
    }

    model.addAttribute("resep", resep);
    return "resep/add-resep";
  }

  @PostMapping(value = "/resep/tambah", params = {"addRowObat"})
  private String addRowObatMultiple(@ModelAttribute ResepModel resep, Model model) {
      if (resep.getListJumlah() == null || resep.getListJumlah().size() == 0) {
        resep.setListJumlah(new ArrayList<>());
      }

      ObatModel obat = new ObatModel();
      JumlahModel jumlah = new JumlahModel();
      resep.getListJumlah().add(jumlah);
      jumlah.setObat(obat);
      List<ObatModel> listObat = obatService.getListObat();

      model.addAttribute("resep", resep);
      model.addAttribute("listObatExisting", listObat);

      return "resep/form-add-resep";
  }

  @PostMapping(value = "/resep/tambah", params = {"deleteRowObat"})
  private String deleteRowResepMultiple(@ModelAttribute ResepModel resep, @RequestParam("deleteRowObat") Integer row, Model model) {
      final Integer rowId = Integer.valueOf(row);
      resep.getListJumlah().remove(rowId.intValue());

      List<ObatModel> listObat = obatService.getListObat();

      model.addAttribute("resep", resep);
      model.addAttribute("listObatExisting", listObat);

      return "resep/form-add-resep";
  }

  @GetMapping("/resep/{idResep}")
  public String viewDetailKaryawanPage(@PathVariable Long idResep, Model model) {
    ResepModel resep = resepService.getResepByIdResep(idResep);
    model.addAttribute("resep", resep);

    return "resep/view-resep";
  }
}