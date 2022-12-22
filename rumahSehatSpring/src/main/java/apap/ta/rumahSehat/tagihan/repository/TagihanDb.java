package apap.ta.rumahSehat.tagihan.repository;

import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    TagihanModel findByKodeTagihan(String kodeTagihan);
    List<TagihanModel> findAllByAppointment_Pasien_Id(String idPasien);

    Optional<TagihanModel> findById(Long id);
}
