package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.dto.PasienDTO;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.model.RoleEnum;
import apap.ta.rumahSehat.user.repository.PasienDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> findAll() {
        return pasienDb.findAll();
    }

    @Override
    public PasienModel addPasien(PasienDTO pasienDTO){
        PasienModel pasienModel = new PasienModel();
        pasienModel.setRole(RoleEnum.Pasien);
        pasienModel.setUsername(pasienDTO.getUsername());
        pasienModel.setNama(pasienDTO.getNama());
        pasienModel.setEmail(pasienDTO.getEmail());
        pasienModel.setPassword(pasienDTO.getPassword());
        pasienModel.setUmur(pasienDTO.getUmur());
        pasienModel.setSaldo(0);
        pasienModel.setListAppointment(new ArrayList<>());

        pasienModel.setPassword(encrypt(pasienModel.getPassword()));

        if (pasienDb.findByUsername(pasienModel.getUsername()) != null) {
            throw new UnsupportedOperationException("Username already taken");
        }
        return pasienDb.save(pasienModel);
    }

    @Override
    public PasienModel findPasienByUsername(String username) {
        return pasienDb.findByUsername(username);
    }

    @Override
    public String encrypt(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public PasienModel topUpSaldo(PasienModel pasien, Integer saldoTambahan) {
        pasien.setSaldo(pasien.getSaldo() + saldoTambahan);
        return pasienDb.save(pasien);
    }
}
