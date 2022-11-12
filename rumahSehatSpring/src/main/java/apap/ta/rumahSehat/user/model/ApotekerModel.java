package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.resep.ResepModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apoteker")
public class ApotekerModel extends UserModel {
    @OneToMany
    List<ResepModel> listResep;
}
