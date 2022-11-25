package apap.ta.rumahSehat.obat.repository;


import apap.ta.rumahSehat.obat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObatDb extends JpaRepository<ObatModel, String> {

}

