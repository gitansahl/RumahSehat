package apap.ta.rumahSehat.resep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

  @GetMapping("/resep/tambah")
  public String addResepFormPage(Model model) {
    ResepModel resep = new ResepModel();
    // List<ObatModel> listObat = obatService.getListObat();

    model.addAttribute("resep", resep);
    // model.addAttribute("listObatExisting", listObat);
    
    return "resep/form-add-resep";
  }

  @PostMapping("/resep/tambah")
  public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
    resepService.addResep(resep);

    model.addAttribute("resep", resep);
    return "resep/add-resep";
  }

  // @PostMapping(value = "/resep/tambah", params = {"addRow"})
  // private String addRowObatMultiple(
  //         @ModelAttribute ResepModel resep,
  //         Model model
  // ){
  //     if (resep.getListObat() == null || resep.getListObat().size() == 0) {
  //         resep.setListObat(new ArrayList<>());
  //     }
  //     resep.getListObat().add(new ObatModel());
  //     List<ObatModel> listObat = obatService.getListObat();

  //     model.addAttribute("resep", resep);
  //     model.addAttribute("listObatExisting", listObat);

  //     return "resep/form-add-resep";
  // }

  // @PostMapping(value = "/resep/tambah", params = {"deleteRow"})
  // private String deleteRowResepMultiple(
  //         @ModelAttribute ResepModel resep,
  //         @RequestParam("deleteRow") Integer row,
  //         Model model
  // ){
  //     final Integer rowId = Integer.valueOf(row);
  //     resep.getListObat().remove(rowId.intValue());

  //     List<ObatModel> listObat = obatService.getListObat();

  //     model.addAttribute("resep", resep);
  //     model.addAttribute("listObatExisting", listObat);

  //     return "resep/form-add-resep";
  // }

  // @PostMapping(value = "/resep/tambah", params = {"save"})
  // private String addResepSubmit(
  //         @ModelAttribute ResepModel resep,
  //         Model model
  // ){
  //     if (resep.getListObat() == null) {
  //       resep.setListObat(new ArrayList<>());
  //     }
  //     for (ObatModel obat : resep.getListObat()) {
  //         obat.setIdResep(resep.getIdResep());
  //     }
  //     resepService.addResep(resep);
  //     System.out.println(resep.getIdResep());
  //     model.addAttribute("code", resep.getIdResep());
  //     return "resep/add-resep";
  // }
}
