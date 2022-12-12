package apap.ta.rumahSehat.tagihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.ta.rumahSehat.tagihan.model.TagihanModel;

public interface TagihanDb extends JpaRepository<TagihanModel,Long> {
  Optional<TagihanModel> findById(Long id);
}
