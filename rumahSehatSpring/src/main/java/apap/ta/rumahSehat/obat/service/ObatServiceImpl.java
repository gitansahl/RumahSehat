package apap.ta.rumahSehat.obat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.repository.ObatDb;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
  @Autowired
  ObatDb obatDb;

  @Override
  public List<ObatModel> getListObat() { 
    return obatDb.findAll();
  }
}
