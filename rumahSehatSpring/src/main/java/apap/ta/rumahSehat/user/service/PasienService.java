package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface PasienService {
    List<PasienModel> findAll();

    PasienModel addPasien(PasienModel pasienModel) throws Exception;

    String encrypt(String password);
}
