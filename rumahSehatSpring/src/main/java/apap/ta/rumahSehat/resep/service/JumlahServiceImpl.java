package apap.ta.rumahSehat.resep.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.rumahSehat.resep.model.JumlahModel;
import apap.ta.rumahSehat.resep.repository.JumlahDb;

@Service
@Transactional
public class JumlahServiceImpl implements JumlahService{
  @Autowired
  JumlahDb jumlahDb;

  @Override
  public void addJumlah(JumlahModel jumlah) {
    jumlahDb.save(jumlah);
  }

  @Override
  public List<JumlahModel> getListJumlah() {
    return jumlahDb.findAll();
  }

  @Override
  public void deleteJumlahByIdObat(String idObat) {
    jumlahDb.deleteJumlahModelByObat_IdObat(idObat);
  }
}
