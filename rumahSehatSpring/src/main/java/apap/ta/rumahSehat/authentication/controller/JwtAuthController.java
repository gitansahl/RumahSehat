package apap.ta.rumahSehat.authentication.controller;

import apap.ta.rumahSehat.authentication.config.JwtTokenUtil;
import apap.ta.rumahSehat.authentication.model.JwtRequest;
import apap.ta.rumahSehat.authentication.model.JwtResponse;
import apap.ta.rumahSehat.authentication.service.JwtUserDetailsService;
import apap.ta.rumahSehat.authentication.setting.Setting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Slf4j
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final String token = jwtTokenUtil.generateToken(userDetails);

            log.info(String.format("%s logged in with token %s.", authenticationRequest.getUsername(), token));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            Setting.response(HttpStatus.OK.value(), new JwtResponse(token))
                    );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Setting.response(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                    );

        }
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED");

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Wrong Password", e);
        }
    }
}
