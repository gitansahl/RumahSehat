package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    List<ApotekerModel> findAll();
    String encrypt(String password);
    ApotekerModel addApoteker(ApotekerModel apotekerModel);
    ApotekerModel getApotekerByUsername(String username);
}
