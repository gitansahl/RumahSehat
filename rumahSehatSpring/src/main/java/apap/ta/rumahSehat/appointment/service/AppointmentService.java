package apap.ta.rumahSehat.appointment.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;

import java.util.List;

public interface AppointmentService {
    void addAppointment(AppointmentModel appointment);

    AppointmentModel getAppointmentByIdAppointment(Long idAppointment);

    AppointmentModel getAppointmentByKodeAppointment(String kodeAppointment);

    List<AppointmentModel> getListAppointment();
}
