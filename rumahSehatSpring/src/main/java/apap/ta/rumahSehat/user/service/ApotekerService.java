package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    List<ApotekerModel> findAll();
    String encrypt(String password);
    ApotekerModel addApoteker(ApotekerModel apotekerModel) throws Exception;
    ApotekerModel getApotekerByUsername(String username);
}
