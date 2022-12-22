package apap.ta.rumahSehat.tagihan.controller;

import apap.ta.rumahSehat.authentication.service.JwtUserDetailsService;
import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.tagihan.service.TagihanService;
import apap.ta.rumahSehat.user.service.PasienService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tagihan")
public class TagihanRestController {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    TagihanService tagihanService;

    @Autowired
    PasienService pasienService;

    @GetMapping("/get")
    public ResponseEntity getTagihan(Authentication authentication) {
        var pasienModel = pasienService.findPasienByUsername(authentication.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        tagihanService.getListTagihanPasien(pasienModel.getId())
                ));
    }

    @GetMapping("/{kodeTagihan}")
    public ResponseEntity getDetailTagihan(@PathVariable String kodeTagihan) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Setting.response(
                        HttpStatus.OK,
                        tagihanService.getTagihanByKodeTagihan(kodeTagihan))
                );
    }

    @PostMapping(value = "/bayar/{kodeTagihan}")
    public ResponseEntity<?> topUpSaldo(Authentication authentication, @PathVariable String kodeTagihan){

        var pasienModel = pasienService.findPasienByUsername(authentication.getName());
        var tagihanModel= tagihanService.getTagihanByKodeTagihan(kodeTagihan);

        if (!tagihanService.bayarTagihan(pasienModel, tagihanModel)) {
            return ResponseEntity.status(HttpStatus.OK).body(Setting.response(HttpStatus.OK.value(), "Saldo tidak cukup"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Setting.response(HttpStatus.OK.value(), "Pembayaran Berhasil"));

    }


}
