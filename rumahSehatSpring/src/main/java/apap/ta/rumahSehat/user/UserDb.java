package apap.ta.rumahSehat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, String > {
    UserModel findByUsername(String username);
}
