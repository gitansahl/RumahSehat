package apap.ta.rumahSehat.appointment.repository;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
    Optional<AppointmentModel> findByKodeAppointment(String kodeAppointment);

    Optional<AppointmentModel> findByIdAppointment(Long idAppointment);

    Optional<AppointmentModel> findAppointmentModelByDokter_IdAndWaktuAwalBetween(String idDokter, LocalDateTime awal, LocalDateTime akhir);
    Optional<AppointmentModel> findAppointmentModelByPasien_IdAndWaktuAwalBetween(String idPasien, LocalDateTime awal, LocalDateTime akhir);

    List<AppointmentModel> findAllByPasien_Id(String idPasien);

    // @Query("SELECT a FROM AppointmentModel a WHERE a.dokter.id = :id ORDER BY a.isDone")
    // List<AppointmentModel> findAllByDokter(@Param("id") String id);

    // @Query("SELECT a FROM AppointmentModel a WHERE a.pasien.id = :id ORDER BY a.isDone")
    // List<AppointmentModel> findAllByPasien(@Param("id") String id);

}
