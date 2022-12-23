package apap.ta.rumahSehat.resep.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ta.rumahSehat.resep.dto.ResepDTO;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.service.ResepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ResepRestController {
  @Qualifier("resepServiceImpl")
  @Autowired
  private ResepService resepService;
  
  // Detail resep
  @GetMapping("/resep/{kodeAppointment}")
  public ResepDTO getResepById(@PathVariable String kodeAppointment, Model model, Authentication authentication) {
      ResepModel resep = resepService.getResepByKodeAppointment(kodeAppointment);
      var resepDTO = resepService.getResepApi(resep);

      log.info(String.format("%s request to view resep", authentication.getName()));

      return resepDTO;
  }
}

