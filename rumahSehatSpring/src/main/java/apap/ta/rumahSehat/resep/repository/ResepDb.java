package apap.ta.rumahSehat.resep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.resep.model.ResepModel;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, Long>{
  ResepModel findByIdResep(Long idResep);

  ResepModel findByAppointment(AppointmentModel appointment);
}