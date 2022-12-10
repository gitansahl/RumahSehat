package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface PasienService {
    List<PasienModel> findAll();

    PasienModel addPasien(PasienModel pasienModel) throws Exception;

    PasienModel findPasienByUsername(String username);

    PasienModel topUpSaldo(PasienModel pasien, Integer saldoTambahan);
    String encrypt(String password);
}
