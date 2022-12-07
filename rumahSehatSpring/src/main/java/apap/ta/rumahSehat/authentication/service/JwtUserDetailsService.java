package apap.ta.rumahSehat.authentication.service;

import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    PasienDb pasienDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasienModel pasienModel = pasienDb.findByUsername(username);

        if (pasienModel == null) {
            throw new UsernameNotFoundException("Username not found.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(pasienModel.getRole().toString()));
        return new User(pasienModel.getUsername(), pasienModel.getPassword(), grantedAuthorities);
    }
}
