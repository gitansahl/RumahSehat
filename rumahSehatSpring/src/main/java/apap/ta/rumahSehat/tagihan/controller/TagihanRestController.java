package apap.ta.rumahSehat.tagihan.controller;

import apap.ta.rumahSehat.authentication.service.JwtUserDetailsService;
import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.tagihan.service.TagihanService;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.service.PasienService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tagihan")
@CrossOrigin("*")
public class TagihanRestController {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    TagihanService tagihanService;

    @Autowired
    PasienService pasienService;

    @GetMapping("/get")
    private ResponseEntity getTagihan(Authentication authentication) {
        PasienModel pasienModel = pasienService.findPasienByUsername(authentication.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        tagihanService.getListTagihanPasien(pasienModel.getId())
                ));
    }

    @GetMapping("/{kodeTagihan}")
    private ResponseEntity getDetailTagihan(@PathVariable String kodeTagihan) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        tagihanService.getTagihanByKodeTagihan(kodeTagihan))
                );
    }

    @RequestMapping(value = "/bayar/{kodeTagihan}", method = RequestMethod.POST)
    public ResponseEntity<?> topUpSaldo(Authentication authentication, @PathVariable String kodeTagihan){

        PasienModel pasienModel = pasienService.findPasienByUsername(authentication.getName());
        TagihanModel tagihanModel= tagihanService.getTagihanByKodeTagihan(kodeTagihan);

        if (!tagihanService.bayarTagihan(pasienModel, tagihanModel)) {
            return ResponseEntity.status(HttpStatus.OK).body(Setting.response(HttpStatus.OK.value(), "Saldo tidak cukup"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Setting.response(HttpStatus.OK.value(), "Pembayaran Berhasil"));

    }


}
