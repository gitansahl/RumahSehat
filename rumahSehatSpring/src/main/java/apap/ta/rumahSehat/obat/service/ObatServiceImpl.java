package apap.ta.rumahSehat.obat.service;


import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{

    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }
}
