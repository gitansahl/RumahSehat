package apap.ta.rumahSehat.resep.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.ta.rumahSehat.resep.model.ResepDTO;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.service.ResepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class ResepRestController {
  @Qualifier("resepServiceImpl")
  @Autowired
  private ResepService resepService;
  
  // @GetMapping("/resep")
  // public String getResepList(Model model) {
  //     model.addAttribute("resep", resepService.getListResep());
  //     return "/";
  // }
  
  // Detail resep
  @GetMapping("/resep/{kodeAppointment}")
  public ResepDTO getResepById(@PathVariable String kodeAppointment, Model model, Authentication authentication) {
      ResepModel resep = resepService.getResepByKodeAppointment(kodeAppointment);
      ResepDTO resepDTO = resepService.getResepApi(resep);

      log.info(String.format("%s request to view resep", authentication.getName()));

      return resepDTO;
  }
}


// import apap.tugas.akhir.rumahsehat.model.DTO.ResepDTO;
// import apap.tugas.akhir.rumahsehat.model.ResepModel;
// import apap.tugas.akhir.rumahsehat.service.ResepService;




// }
