package apap.ta.rumahSehat.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ta.rumahSehat.user.model.AdminModel;

@Repository
public interface AdminDb extends JpaRepository<AdminModel,String> {
  AdminModel findByUsername(String username);
}
