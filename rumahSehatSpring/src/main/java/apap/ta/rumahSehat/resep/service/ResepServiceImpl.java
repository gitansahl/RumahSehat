package apap.ta.rumahSehat.resep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.repository.ResepDb;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.resep.model.JumlahDTO;
import apap.ta.rumahSehat.resep.model.JumlahModel;
import apap.ta.rumahSehat.resep.model.ResepDTO;
import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.resep.repository.ResepDb;
import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.appointment.service.AppointmentService;
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
  AppointmentService appointmentService;

  @Override
  public void addResep(ResepModel resep) {
    resepDb.save(resep);
  }

  @Override
  public List<ResepModel> getListResep() { 
    return resepDb.findAll();
  }

  @Override
  public ResepModel getResepByIdResep(Long idResep) {
    return resepDb.findByIdResep(idResep);
  }

  @Override
  public ResepModel getResepByKodeAppointment(String kodeAppointment) {
    AppointmentModel appointment = appointmentService.getAppointmentByKodeAppointment(kodeAppointment);

    return resepDb.findByAppointment(appointment);
  }
  
  @Override
  public ResepDTO getResepApi (ResepModel resep){
    List<JumlahDTO> listObat = new ArrayList<JumlahDTO>();

    for (JumlahModel obat : resep.getListJumlah()) {
        JumlahDTO jumlahDTO = new JumlahDTO(obat.getObat().getNamaObat(), obat.getKuantitas());
        listObat.add(jumlahDTO);
    }

    //Membuat model DTO baru
    ResepDTO apiResep = new ResepDTO(resep.getIdResep(), resep.getAppointment().getDokter().getNama(), resep.getAppointment().getPasien().getNama(), listObat);
    
    //Cek apoteker yang akan mengkonfirmasi
    if (resep.getConfirmer() == null) {
      apiResep.setApoteker("-");
    } else {
      apiResep.setApoteker(resep.getConfirmer().getNama());
    }

    //Cek status resep
    if (resep.getIsDone() != true){
      apiResep.setStatus("BELUM SELESAI");
    } else {
      apiResep.setStatus("SELESAI");
    }

    return apiResep;
  }
}
