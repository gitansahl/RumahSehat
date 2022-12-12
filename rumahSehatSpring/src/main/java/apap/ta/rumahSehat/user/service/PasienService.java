package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.dto.PasienDTO;
import apap.ta.rumahSehat.user.model.PasienModel;

import java.util.List;

public interface PasienService {
    List<PasienModel> findAll();

    PasienModel addPasien(PasienDTO pasienDTO);

    PasienModel findPasienByUsername(String username);

    String encrypt(String password);
    PasienModel topUpSaldo(PasienModel pasien, Integer saldoTambahan);
}
