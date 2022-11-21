package apap.ta.rumahSehat.resep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ta.rumahSehat.obat.ObatModel;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.service.ResepService;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResepController {
  @Qualifier("resepServiceImpl")
  @Autowired
  private ResepService resepService;

  // @Qualifier("obatServiceImpl")
  // @Autowired
  // private ResepService obatService;

  @GetMapping("/resep")
  public String listKaryawan(Model model) {
    List<ResepModel> listResep = resepService.getListResep();
    model.addAttribute("listResep", listResep);
    return "resep/viewall-resep";
  }

  @GetMapping("/resep/{idResep}")
  public String viewDetailKaryawanPage(@PathVariable Long idResep, Model model) {
    ResepModel resep = resepService.getResepByIdResep(idResep);
    model.addAttribute("resep", resep);

    return "resep/view-resep";
  }

  @GetMapping("resep/tambah")
  public String addResepFormPage(Model model) {
      ResepModel resep = new ResepModel();
      // List<ObatModel> listObat = obatService.getListObat();

      model.addAttribute("resep", resep);
      // model.addAttribute("listObatExisting", listObat);
      return "resep/form-add-resep";
  }

  @PostMapping("resep/tambah")
  public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
      resepService.addResep(resep);

      model.addAttribute("resep", resep);
      return "resep/add-resep";
  }
}
