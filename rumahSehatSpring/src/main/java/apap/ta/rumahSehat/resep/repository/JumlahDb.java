package apap.ta.rumahSehat.resep.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ta.rumahSehat.resep.model.JumlahModel;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, Long>{
  JumlahModel findByIdJumlah(Long idJumlah);

  Optional<JumlahModel> deleteJumlahModelByObat_IdObat(String idObat);
}