package apap.ta.rumahSehat.appointment.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
