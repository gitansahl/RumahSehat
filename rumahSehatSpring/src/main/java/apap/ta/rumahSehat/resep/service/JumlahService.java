package apap.ta.rumahSehat.resep.service;

import java.util.List;
import apap.ta.rumahSehat.resep.model.JumlahModel;

public interface JumlahService {
  void addJumlah(JumlahModel jumlah);

  List<JumlahModel> getListJumlah();

  void deleteJumlahByIdObat(String idObat);
}
