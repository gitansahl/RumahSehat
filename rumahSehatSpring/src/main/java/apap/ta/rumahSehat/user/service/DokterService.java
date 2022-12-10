package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> findAll();
    String encrypt(String password);
    DokterModel addDokter(DokterModel dokterModel) throws Exception;
    DokterModel findDokterByUsername(String username);
}
