package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;

import java.util.List;

public interface PasienService {
    List<PasienModel> findAll();
}
