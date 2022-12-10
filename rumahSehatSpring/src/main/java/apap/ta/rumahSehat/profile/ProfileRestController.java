package apap.ta.rumahSehat.profile;

import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.service.PasienService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import apap.ta.rumahSehat.authentication.service.JwtUserDetailsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProfileRestController {
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    PasienService pasienService;

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public ResponseEntity getUserInfo(Principal principal){
        ObjectMapper objectMapper = new ObjectMapper();
        String username = userDetailsService.loadUserByUsername(principal.getName()).getUsername();
        PasienModel pasien = pasienService.findPasienByUsername(username);
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("username", pasien.getUsername());
        userDetails.put("nama", pasien.getNama());
        userDetails.put("email", pasien.getEmail());
        userDetails.put("saldo", pasien.getSaldo());
        userDetails.put("umur", pasien.getUmur());

        try {
            String userDetailsResponse = objectMapper.writeValueAsString(userDetails);
            return ResponseEntity.ok(userDetailsResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/topUp", method = RequestMethod.POST)
    public ResponseEntity<?> topUpSaldo(Principal principal, @RequestBody String saldoTambahanJson){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNodeRoot = objectMapper.readTree(saldoTambahanJson);
            JsonNode jsonSaldoTambahan = jsonNodeRoot.get("saldoTambahan");
            Integer saldoTambahan = jsonSaldoTambahan.asInt();

            String username = userDetailsService.loadUserByUsername(principal.getName()).getUsername();
            PasienModel pasien = pasienService.findPasienByUsername(username);

            pasienService.topUpSaldo(pasien, saldoTambahan);

            return ResponseEntity.status(HttpStatus.OK).body(Setting.response(HttpStatus.OK.value(), pasien.getSaldo()));
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                    );
        }
    }
}
