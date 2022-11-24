package apap.ta.rumahSehat.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.resep.model.ResepModel;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String>{
  AppointmentModel findByKodeAppointment(String kodeAppointment);
}
