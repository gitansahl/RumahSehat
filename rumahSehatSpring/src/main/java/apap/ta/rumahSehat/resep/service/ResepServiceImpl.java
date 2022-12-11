package apap.ta.rumahSehat.resep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.repository.ResepDb;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.obat.repository.ObatDb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResepServiceImpl implements ResepService{
  @Autowired
  ResepDb resepDb;

  @Autowired
  AppointmentDb appointmentDb;

  @Autowired
  ObatDb obatDb;

  @Override //, Long idAppointment
  public void addResep(ResepModel resep) {  //nnt harus diganti, ambil parameter id appointment buat ngecek appointmentnya udah kelar apa belom, kalo belom isDone nya false
    // Optional<AppointmentModel> appointment = appointmentDb.findByIdAppointment(idAppointment);

    // if (appointment.getIsDone == true) {
    //   return true;
    // }
    resepDb.save(resep);
    
    // return false;
  }

  @Override
  public List<ResepModel> getListResep() { 
    return resepDb.findAll();
  }

  @Override
  public ResepModel getResepByIdResep(Long idResep) {
    return resepDb.findByIdResep(idResep);
  }
}
