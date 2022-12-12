package apap.ta.rumahSehat.tagihan.repository;

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
}
