package apap.ta.rumahSehat.tagihan.repository;

<<<<<<< HEAD
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.user.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    TagihanModel findByKodeTagihan(String kodeTagihan);
    List<TagihanModel> findAllByAppointment_Pasien_Id(String idPasien);

    Optional<TagihanModel> findById(Long id);
=======
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.ta.rumahSehat.tagihan.model.TagihanModel;

public interface TagihanDb extends JpaRepository<TagihanModel,Long> {
  Optional<TagihanModel> findById(Long id);
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
}
