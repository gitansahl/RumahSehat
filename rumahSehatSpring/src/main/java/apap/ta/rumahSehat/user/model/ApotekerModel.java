package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.resep.model.ResepModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apoteker")
<<<<<<< HEAD
public class ApotekerModel extends UserModel {
=======
public class ApotekerModel extends UserModel implements Serializable {
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b

    @OneToMany(mappedBy = "confirmer", fetch = FetchType.LAZY)
    @JsonIgnore
    List<ResepModel> listResep;
}
