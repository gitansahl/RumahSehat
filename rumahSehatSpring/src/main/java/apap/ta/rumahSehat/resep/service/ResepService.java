package apap.ta.rumahSehat.resep.service;

import apap.ta.rumahSehat.resep.model.ResepModel;
import java.util.List;

public interface ResepService {
  void addResep(ResepModel resep);

  List<ResepModel> getListResep();

  ResepModel getResepByIdResep(Long idResep);
}
