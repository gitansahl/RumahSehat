package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.resep.model.ResepModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "apoteker")
public class ApotekerModel extends UserModel {
    @OneToMany
    List<ResepModel> listResep;

    // public ApotekerModel() {
    // }

    // public ApotekerModel(String nama, RoleEnum role, String username, String password, String email) {
    //     super(nama, role, username, password, email);
    // }

}
