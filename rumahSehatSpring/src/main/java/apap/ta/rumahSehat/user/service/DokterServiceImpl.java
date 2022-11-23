package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> findAll() {
        return dokterDb.findAll();
    }
}