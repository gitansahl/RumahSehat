package apap.ta.rumahSehat.resep.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.service.ObatService;
import apap.ta.rumahSehat.resep.model.JumlahModel;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.service.JumlahService;
import apap.ta.rumahSehat.resep.service.ResepService;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.tagihan.service.TagihanService;
import apap.ta.rumahSehat.user.model.ApotekerModel;
import apap.ta.rumahSehat.user.service.ApotekerService;

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
  
  @Qualifier("apotekerServiceImpl")
  @Autowired
  private ApotekerService apotekerService;
  
  @Qualifier("tagihanServiceImpl")
  @Autowired
  private TagihanService tagihanService;

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
      // if (obatDb.getStok() < jumlah.getKuantitas()) {
      //     model.addAttribute("resep", resep);
      //     return "resep/cant-add-resep";
      // } else {
      //     obatDb.setStok(obatDb.getStok() - jumlah.getKuantitas());
      //     jumlah.setObat(obatDb);
      // }
    }

    LocalDateTime now = LocalDateTime.now();
    resep.setIsDone(false);
    resep.setCreatedAt(now);
    resep.setAppointment(appointmentService.getAppointmentByIdAppointment(resep.getAppointment().getIdAppointment()));

    resepService.addResep(resep);
    for (JumlahModel jumlahjumlah : resep.getListJumlah()) {
      // ObatModel obat = jumlahjumlah.getObat();
      jumlahService.addJumlah(jumlahjumlah);
      // obatService.updateStok(obat);
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
  public String viewDetailResepPage(@PathVariable Long idResep, Model model) {
    ResepModel resep = resepService.getResepByIdResep(idResep);
    
    String apoteker = "-";
    String dokter = resep.getAppointment().getDokter().getNama();
    String pasien = resep.getAppointment().getPasien().getNama();
    String status = "BELUM SELESAI";
    List<JumlahModel> listJumlah = resep.getListJumlah();

    if (resep.getIsDone()) {
      status = "SELESAI";
      apoteker = resep.getConfirmer().getNama();
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    ApotekerModel usernameApoteker = apotekerService.getApotekerByUsername(username);

    if (usernameApoteker != null){
        model.addAttribute("role", "Apoteker");
    }


    model.addAttribute("resep", resep);
    model.addAttribute("apoteker", apoteker);
    model.addAttribute("dokter", dokter);
    model.addAttribute("pasien", pasien);
    model.addAttribute("status", status);
    model.addAttribute("listJumlah", listJumlah);
    return "resep/view-resep";
  }

  @PostMapping("/resep/konfirmasi")
  public String konfirmasiResep(@ModelAttribute ResepModel resep, Model model) {
      ResepModel temp = resepService.getResepByIdResep(resep.getIdResep());
      int tagihan = 0;

      for (JumlahModel jumlah : temp.getListJumlah()) {
        jumlah.setResep(temp);
        ObatModel obat = jumlah.getObat();
        ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());

        if (jumlah.getKuantitas() <= obatDb.getStok()) {
          resep.setIsDone(true);
          int dipakai = jumlah.getKuantitas();
          int stokAsli = obatDb.getStok();
          int harga = obatDb.getHarga();

          jumlah.setObat(obatDb);
          obatDb.setStok(stokAsli - dipakai);
          tagihan = tagihan + (harga * dipakai);
        }
        else {
          model.addAttribute("resep", temp);
          tagihan = 0;
          return "cant-confirm-resep";
        }
      }

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      ApotekerModel apoteker = apotekerService.getApotekerByUsername(username);

      temp.setIsDone(true);
      temp.setConfirmer(apoteker);
      resepService.addResep(temp);
      Long idAppointment = temp.getAppointment().getIdAppointment();

      AppointmentModel appointment = appointmentService.getAppointmentByIdAppointment(idAppointment);
      appointment.setIsDone(true);
      appointment.setResep(temp);
      appointmentService.addAppointment(appointment);

      //Ngeset tagihannya
      TagihanModel tempTagihan = new TagihanModel();
      tempTagihan.setAppointment(temp.getAppointment());
      tempTagihan.setTerbayar(false);
      LocalDateTime now = LocalDateTime.now();
      tempTagihan.setTanggalDibuat(now);
      tagihan = tagihan + (appointment.getDokter().getTarif()).intValue();
      tempTagihan.setJumlahTagihan(Long.valueOf(tagihan));
      tagihanService.addTagihan(tempTagihan);

      TagihanModel currTagihan = tagihanService.getTagihanById(tempTagihan.getId());
      currTagihan.setKodeTagihan("BILL-"+String.valueOf(currTagihan.getId()));
      tagihanService.addTagihan(currTagihan);

      appointment.setTagihan(tagihanService.getTagihanById(currTagihan.getId()));
      appointmentService.addAppointment(appointment);

      model.addAttribute("resep", temp);

      return "confirm-resep";
  }
}