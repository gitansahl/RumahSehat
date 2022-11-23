package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.ApotekerModel;
import apap.ta.rumahSehat.user.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<ApotekerModel> findAll() {
        return apotekerDb.findAll();
    }
}
