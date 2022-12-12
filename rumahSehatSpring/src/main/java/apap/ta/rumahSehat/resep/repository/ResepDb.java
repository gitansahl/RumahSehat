package apap.ta.rumahSehat.resep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
=======

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import apap.ta.rumahSehat.resep.model.ResepModel;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, Long>{
  ResepModel findByIdResep(Long idResep);
<<<<<<< HEAD
=======
  ResepModel findByAppointment(AppointmentModel appointment);
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
}