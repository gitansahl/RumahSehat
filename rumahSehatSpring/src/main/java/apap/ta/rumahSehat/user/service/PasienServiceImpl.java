package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> findAll() {
        return pasienDb.findAll();
    }
}
