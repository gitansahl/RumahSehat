package apap.ta.rumahSehat.authentication.service;

import apap.ta.rumahSehat.user.repository.UserDb;
import apap.ta.rumahSehat.user.model.UserModel;
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
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userDb.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Akun dengan username " + username + " tidak ditemukan.");
        }

        if (user.getRole().toString().equals("Pasien")) {
            throw new UsernameNotFoundException("Pasien hanya dapat mengakses melalui mobile");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
