package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.ApotekerModel;
import apap.ta.rumahSehat.user.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<ApotekerModel> findAll() {
        return apotekerDb.findAll();
    }

    @Override
    public String encrypt(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public ApotekerModel addApoteker(ApotekerModel apotekerModel){
        apotekerModel.setPassword(encrypt(apotekerModel.getPassword()));
        return apotekerDb.save(apotekerModel);
    }

    @Override
    public ApotekerModel getApotekerByUsername(String username){
        return apotekerDb.findByUsername(username);
    }
}
