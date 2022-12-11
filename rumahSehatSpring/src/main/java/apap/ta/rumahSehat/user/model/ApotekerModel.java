package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.resep.model.ResepModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apoteker")
public class ApotekerModel extends UserModel {

    @OneToMany(mappedBy = "confirmer", fetch = FetchType.LAZY)
    @JsonIgnore
    List<ResepModel> listResep;
}
