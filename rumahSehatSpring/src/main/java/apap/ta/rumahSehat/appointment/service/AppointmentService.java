package apap.ta.rumahSehat.appointment.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    void addAppointment(AppointmentModel appointment);

    AppointmentModel getAppointmentByIdAppointment(Long idAppointment);

    AppointmentModel getAppointmentByKodeAppointment(String kodeAppointment);

    List<AppointmentModel> getListAppointment();

    boolean isValid(AppointmentModel appointmentModel);

    List<AppointmentModel> getPasienListAppointment(String id);

    List<AppointmentModel> getAppointmentInRange(LocalDateTime awal, LocalDateTime akhir);
}
