package apap.ta.rumahSehat.resep.service;

import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.resep.model.ResepModel;
import java.util.List;

public interface ResepService {
  void addResep(ResepModel resep); //, Long idAppointment

  List<ResepModel> getListResep();

  ResepModel getResepByIdResep(Long idResep);
}
