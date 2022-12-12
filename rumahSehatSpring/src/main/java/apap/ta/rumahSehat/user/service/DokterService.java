package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> findAll();
    String encrypt(String password);
    DokterModel addDokter(DokterModel dokterModel);
    DokterModel findDokterByUsername(String username);
}
