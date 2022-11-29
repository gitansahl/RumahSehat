package apap.ta.rumahSehat.authentication.config;

import apap.ta.rumahSehat.authentication.service.JwtUserDetailsService;
import apap.ta.rumahSehat.authentication.service.UserDetailServiceImpl;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Configuration
    @Order(2)
    public static class UILoginWebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        JwtRequestFilter jwtRequestFilter;
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login-sso", "/validate-ticket").permitAll()
                    .antMatchers("/").hasAnyAuthority("Admin", "Apoteker", "Dokter")
                    .antMatchers("/user/**").hasAuthority("Admin")
                    .antMatchers("/error").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and()
                    .sessionManagement().sessionFixation().newSession().maximumSessions(1);
        }

        private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

        @Autowired
        private UserDetailServiceImpl userDetailsService;

        @Autowired
        public void configAuthenticationWeb(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        }
    }

    @Configuration
    @Order(1)
    public static class RestApiWebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        private JwtAuthEntryPoint jwtAuthEntryPoint;

        private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            // configure AuthenticationManager so that it knows from where to load
            // user for matching credentials
            // Use BCryptPasswordEncoder
            auth.userDetailsService(jwtUserDetailsService).passwordEncoder(encoder);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Autowired
        private JwtUserDetailsService jwtUserDetailsService;

        @Autowired
        JwtRequestFilter jwtRequestFilter;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/api/**").cors()
                    .and()
                    .csrf().disable()
                    // dont authenticate this particular request
                    .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/**").hasAuthority("Pasien")

                    // all other requests need to be authenticated
                    .anyRequest().authenticated().and()

                    // make sure we use stateless session; session won't be used to
                    // store user's state.
                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Add a filter to validate the tokens with every request
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }


    }
}
