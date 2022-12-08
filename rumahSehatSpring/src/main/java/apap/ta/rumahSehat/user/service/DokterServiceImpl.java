package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> findAll() {
        return dokterDb.findAll();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public DokterModel addDokter(DokterModel dokterModel) throws Exception {
        dokterModel.setPassword(encrypt(dokterModel.getPassword()));
        return dokterDb.save(dokterModel);
    }

    @Override
    public DokterModel findDokterByUsername(String username) {
        return dokterDb.findByUsername(username);
    }
}
