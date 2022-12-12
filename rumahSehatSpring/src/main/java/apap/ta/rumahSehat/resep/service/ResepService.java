package apap.ta.rumahSehat.resep.service;

import apap.ta.rumahSehat.obat.model.ObatModel;
<<<<<<< HEAD
=======
import apap.ta.rumahSehat.resep.model.ResepDTO;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import apap.ta.rumahSehat.resep.model.ResepModel;
import java.util.List;

public interface ResepService {
  void addResep(ResepModel resep); //, Long idAppointment

  List<ResepModel> getListResep();

  ResepModel getResepByIdResep(Long idResep);
<<<<<<< HEAD
=======

  ResepModel getResepByKodeAppointment(String kodeAppointment);

  ResepDTO getResepApi(ResepModel resep);
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
}
