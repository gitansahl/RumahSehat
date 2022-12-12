package apap.ta.rumahSehat.appointment.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.model.PasienModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public void addAppointment(AppointmentModel appointment) { appointmentDb.save(appointment); }

    @Override
    public AppointmentModel getAppointmentByIdAppointment(Long idAppointment) {
        Optional<AppointmentModel> appointment = appointmentDb.findByIdAppointment(idAppointment);
        if (appointment.isPresent()) {
            return appointment.get();
        } else return null;
    }

    @Override
    public AppointmentModel getAppointmentByKodeAppointment(String kodeAppointment) {
        Optional<AppointmentModel> appointment = appointmentDb.findByKodeAppointment(kodeAppointment);
        if (appointment.isPresent()) {
            return appointment.get();
        } else return null;
    }

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    // @Override
    // public List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter) {
    //     return appointmentDb.findAllByDokter(dokter.getId());
    // }

    // @Override
    // public List<AppointmentModel> getListAppointmentByPasien(PasienModel pasien) {
    //     return appointmentDb.findAllByPasien(pasien.getId());
    // }


    @Override
    public boolean isValid(AppointmentModel appointmentModel) {
        LocalDateTime awal = appointmentModel.getWaktuAwal();
        String idDokter = appointmentModel.getDokter().getId();
        String idPasien = appointmentModel.getPasien().getId();

        if (
                appointmentDb
                        .findAppointmentModelByDokter_IdAndWaktuAwalBetween(
                                idDokter,
                                awal,
                                awal.plusHours(1)
                        ).isPresent()
        ) return false;

        if (
                appointmentDb
                        .findAppointmentModelByDokter_IdAndWaktuAwalBetween(
                                idDokter,
                                awal.minusHours(1),
                                awal
                        ).isPresent()
        ) return false;

        if (
                appointmentDb
                        .findAppointmentModelByPasien_IdAndWaktuAwalBetween(
                                idPasien,
                                awal,
                                awal.plusHours(1)
                        ).isPresent()
        ) return false;

        if (
                appointmentDb
                        .findAppointmentModelByPasien_IdAndWaktuAwalBetween(
                                idPasien,
                                awal.minusHours(1),
                                awal
                        ).isPresent()
        ) return false;

        else return true;
    }

    @Override
    public List<AppointmentModel> getPasienListAppointment(String id) {
        return appointmentDb.findAllByPasien_Id(id);
    }

    @Override
    public void finishAppointment(AppointmentModel appointment) {
        appointment.setIsDone(true);
        appointmentDb.save(appointment);
    }

    @Override
    public List<AppointmentModel> getAppointmentInRange(LocalDateTime awal, LocalDateTime akhir) {
        return appointmentDb.findAllByWaktuAwalBetween(awal, akhir);
    }
}
