package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;

import java.util.List;

public interface PasienService {
    List<PasienModel> findAll();

    PasienModel addPasien(PasienModel pasienModel);

    PasienModel findPasienByUsername(String username);

    String encrypt(String password);
}
