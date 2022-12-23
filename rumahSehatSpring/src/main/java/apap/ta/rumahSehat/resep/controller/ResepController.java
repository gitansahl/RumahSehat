package apap.ta.rumahSehat.resep.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import apap.ta.rumahSehat.resep.dto.ResepDTOWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import apap.ta.rumahSehat.user.service.DokterService;
import apap.ta.rumahSehat.user.service.UserService;

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
  
  @Qualifier("dokterServiceImpl")
  @Autowired
  private DokterService dokterService;

  @Qualifier("tagihanServiceImpl")
  @Autowired
  private TagihanService tagihanService;
  
  @Qualifier("userServiceImpl")
  @Autowired
  private UserService userService;

  @GetMapping("/resep")
  public String listResep(Model model) {
    List<ResepModel> listResep = resepService.getListResep();
    model.addAttribute("listResep", listResep);
    return "resep/viewall-resep";
  }

  @GetMapping("/resep/tambah/{idAppointment}")
  public String addResepFormPage(
    Model model, 
    @PathVariable Long idAppointment) {
    var resep = new ResepDTOWeb();
    resep.setAppointment(appointmentService.getAppointmentByIdAppointment(idAppointment));
    
    List<ObatModel> listObat = obatService.getListObat();

    var jumlah = new JumlahModel();
    var obat = new ObatModel();
    jumlah.setObat(obat);

    List<JumlahModel> listJumlah = new ArrayList<>();
    resep.setListJumlah(listJumlah);
    resep.getListJumlah().add(jumlah);

    model.addAttribute("resep", resep);
    model.addAttribute("listObatExisting", listObat);
    AppointmentModel appointment = appointmentService.getAppointmentByIdAppointment(idAppointment);
    model.addAttribute("appoinment", appointment);
    
    return "resep/form-add-resep";
  }
  
  @PostMapping(value = "/resep/tambah/", params = {"addRowObat"})
  public String addRowObatMultiple(
          @ModelAttribute ResepDTOWeb resep,
          Model model) {

      if (resep.getListJumlah() == null || resep.getListJumlah().isEmpty()) {
          resep.setListJumlah(new ArrayList<>());
      }
      model.addAttribute("resep", resep);

      var jumlah = new JumlahModel();
      resep.getListJumlah().add(jumlah);
      
      var obat = new ObatModel();
      jumlah.setObat(obat);
     
      List<ObatModel> listObat = obatService.getListObat();
      model.addAttribute("listObatExisting", listObat);
        
      return "resep/form-add-resep";
  }

  @PostMapping(value = "/resep/tambah/", params = {"deleteRowObat"})
  public String deleteRowResepMultiple(
          @ModelAttribute ResepDTOWeb resep,
          @RequestParam("deleteRowObat") Integer row,
          Model model) {

      resep.getListJumlah().remove(row.intValue());
      model.addAttribute("resep", resep);

      List<ObatModel> listObat = obatService.getListObat();
      model.addAttribute("listObatExisting", listObat);
  
      return "resep/form-add-resep";
  }
    
  @PostMapping(value = "/resep/tambah", params="save")
  public String addResepSubmitPage(
          @ModelAttribute ResepDTOWeb resepDTOWeb,
          Model model) {

      var resep = new ResepModel();
      resep.setAppointment(resepDTOWeb.getAppointment());
      resep.setListJumlah(resepDTOWeb.getListJumlah());

      for (JumlahModel jumlah : resep.getListJumlah()) {
          jumlah.setResep(resep);
      }

    // Default value for isDone
      resep.setIsDone(false);
      var now = LocalDateTime.now();
      resep.setCreatedAt(now);
      resep.setAppointment(appointmentService.getAppointmentByIdAppointment(resep.getAppointment().getIdAppointment()));

      resepService.addResep(resep);
      model.addAttribute("resep", resep);

      for (JumlahModel jumlahjumlah : resep.getListJumlah()) {
          jumlahService.addJumlah(jumlahjumlah);
      }

      return "resep/add-resep";
  }

  @GetMapping("/resep/{idResep}")
  public String viewDetailResepPage(
    @PathVariable Long idResep, 
    Model model) {
    ResepModel resep = resepService.getResepByIdResep(idResep);
    List<JumlahModel> listJumlah = resep.getListJumlah();
    
    String dokter = resep.getAppointment().getDokter().getNama();
    String pasien = resep.getAppointment().getPasien().getNama();
    var status = "BELUM SELESAI";
    var apoteker = "BELUM ADA";
    
    if (resep.getIsDone()) {
      status = "SELESAI";
      apoteker = resep.getConfirmer().getNama();
    }
    
    var auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    ApotekerModel usernameApoteker = apotekerService.getApotekerByUsername(username);

    if (usernameApoteker != null){
      model.addAttribute("role", "Apoteker");
    }
    

    model.addAttribute("resep", resep);
    model.addAttribute("dokter", dokter);
    model.addAttribute("pasien", pasien);
    model.addAttribute("status", status);
    model.addAttribute("apoteker", apoteker);
    model.addAttribute("listJumlah", listJumlah);
    return "resep/view-resep";
  }

  @PostMapping("/resep/konfirmasi/{idResep}")
  public String konfirmasiResep(
          @PathVariable("idResep") Long idResep,
          Model model) {

      var temp = resepService.getResepByIdResep(idResep);
      List<JumlahModel> listJumlah = temp.getListJumlah();
      Integer tagihan = 0;

      for (JumlahModel jumlah : listJumlah) {
          jumlah.setResep(temp);
          int jumlahObat = jumlah.getKuantitas();
          ObatModel obat = jumlah.getObat();
          Integer harga = obat.getHarga() * jumlahObat;

          if (jumlah.getKuantitas() <= obat.getStok()) {
              int stok = obat.getStok();

              jumlah.setObat(obat);
              obat.setStok(stok - jumlahObat);
              tagihan += harga;

          } else {
              model.addAttribute("resep", temp);
              return "resep/cant-confirm-resep";
          }
      }

      //Mengambil model apoteker yang sedang login
      var auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      ApotekerModel apoteker = apotekerService.getApotekerByUsername(username);
      temp.setConfirmer(apoteker);

      //Membuat status resep selesai
      temp.setIsDone(true);
      resepService.addResep(temp);
      
      //Membuat status appointment selesai
      Long idAppointment = temp.getAppointment().getIdAppointment();
      AppointmentModel appointment = appointmentService.getAppointmentByIdAppointment(idAppointment);
      appointment.setIsDone(true);
      appointment.setResep(temp);
      appointmentService.addAppointment(appointment);

      //Membuat tagihan awal
      var tempTagihan = new TagihanModel();
      var now = LocalDateTime.now();
      tempTagihan.setTanggalDibuat(now);
      tempTagihan.setAppointment(temp.getAppointment());
      tempTagihan.setTerbayar(false);
      tagihan += Integer.parseInt(Integer.toString((appointment.getDokter().getTarif())));
      tempTagihan.setJumlahTagihan(Long.valueOf(tagihan));
      tagihanService.addTagihan(tempTagihan);

      TagihanModel tagihanAsli = tagihanService.getTagihanById(tempTagihan.getId());
      tagihanAsli.setKodeTagihan("BILL-" + tagihanAsli.getId());
      tagihanService.addTagihan(tagihanAsli);
      appointment.setTagihan(tagihanService.getTagihanById(tagihanAsli.getId()));
      appointmentService.addAppointment(appointment);
      
      model.addAttribute("resep", temp);
      return "resep/confirm-resep";
  }
}