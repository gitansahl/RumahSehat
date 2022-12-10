package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.repository.PasienDb;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> findAll() {
        return pasienDb.findAll();
    }

    @Override
    public PasienModel addPasien(PasienModel pasienModel){
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public PasienModel topUpSaldo(PasienModel pasien, Integer saldoTambahan) {
        pasien.setSaldo(pasien.getSaldo() + saldoTambahan);
        return pasienDb.save(pasien);
    }
}
