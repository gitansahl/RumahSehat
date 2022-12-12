package apap.ta.rumahSehat.authentication.controller;

import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.authentication.xml.Attributes;
import apap.ta.rumahSehat.authentication.xml.ServiceResponses;
import apap.ta.rumahSehat.user.model.AdminModel;
import apap.ta.rumahSehat.user.model.RoleEnum;
import apap.ta.rumahSehat.user.model.UserModel;
import apap.ta.rumahSehat.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
public class AuthController {
    private static final String redirect = "redirect:";
    @Autowired
    ServerProperties serverProperties;
    private WebClient webClient = WebClient.builder().build();

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "authentication/login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(@RequestParam(value = "ticket", required = false) String ticket,
                                      HttpServletRequest request
    ){
        var serviceResponses = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponses.class).block();

        if (serviceResponses ==  null) {
            throw new NullPointerException();
        }

        var attributes = serviceResponses.getAuthenticationSuccess().getAttributes();

        String username = serviceResponses.getAuthenticationSuccess().getUser();

        if (!userService.isWhitelist(username)) {
            log.info(String.format("%s non whitelist trying to log in.", username));

            return new ModelAndView(redirect + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
        }

        UserModel user = userService.getUserByUsername(username);

        if (user == null) {
            user = new AdminModel();
            user.setEmail(username + "@ui.ac.id");
            user.setNama(attributes.getNama());
            user.setPassword("rumahsehat");
            user.setUsername(username);
            user.setRole(RoleEnum.Admin);
            userService.addUser(user);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        log.info(String.format("%s logged in with SSO.", authentication.getName()));

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView(redirect+ Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        log.info(String.format("%s logged out.", principal.getName()));

        UserModel user = userService.getUserByUsername(principal.getName());
        if (!user.getRole().equals(RoleEnum.Admin)) {
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView(redirect + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }


}
