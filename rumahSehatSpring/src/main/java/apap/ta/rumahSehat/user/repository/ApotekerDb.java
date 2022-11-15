package apap.ta.rumahSehat.user.repository;

import apap.ta.rumahSehat.user.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, String> {
}
