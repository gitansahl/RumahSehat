package apap.ta.rumahSehat.user.repository;

import apap.ta.rumahSehat.user.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, String> {
}
