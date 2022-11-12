package apap.ta.rumahSehat.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "admin")
public class AdminModel extends UserModel {

}
