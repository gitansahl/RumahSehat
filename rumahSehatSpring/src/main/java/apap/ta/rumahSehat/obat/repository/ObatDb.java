package apap.ta.rumahSehat.obat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ta.rumahSehat.obat.model.ObatModel;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, Long>{
  ObatModel findByIdObat(Long idObat);
}