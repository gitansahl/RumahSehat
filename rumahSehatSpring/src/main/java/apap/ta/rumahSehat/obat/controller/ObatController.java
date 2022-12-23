package apap.ta.rumahSehat.obat.controller;

import apap.ta.rumahSehat.obat.dto.ObatDTO;
import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ObatController {

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @GetMapping("/obat")
    public String viewallObat(Authentication authentication, Model model) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))
                || authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Apoteker"))) {
            List<ObatModel> listObat = obatService.getListObat();
            model.addAttribute("listObat", listObat);
            return "obat/viewall-obat";
        } else {
            return "error/403";
        }
    }

    @GetMapping("/obat/update-stock/{idObat}")
    public String updateStockFormPage(@PathVariable String idObat, Model model){
        ObatModel obatDb = obatService.getObatByIdObat(idObat);

        var obat = new ObatDTO();
        obat.setIdObat(obatDb.getIdObat());
        obat.setNamaObat(obatDb.getNamaObat());
        obat.setStok(obatDb.getStok());
        obat.setHarga(obatDb.getHarga());

        model.addAttribute("obat", obat);
        return "obat/form-update-stock-obat";
    }

    @PostMapping("/obat/update-stock")
    public String updateStockSubmitPage(@ModelAttribute ObatDTO obatDTO, Model model, Authentication authentication){

        var obat = new ObatModel();
        obat.setIdObat(obatDTO.getIdObat());
        obat.setNamaObat(obatDTO.getNamaObat());
        obat.setHarga(obatDTO.getHarga());
        obat.setStok(obatDTO.getStok());

        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Apoteker"))){
            ObatModel updateStock = obatService.updateStok(obat);
            model.addAttribute("namaObat", updateStock.getNamaObat());
            return "obat/update-stock-obat";
        }
        else {
            return "error/403";
        }
    }
}
