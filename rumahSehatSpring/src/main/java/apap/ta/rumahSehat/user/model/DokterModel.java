package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import com.sun.istack.NotNull;
=======
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
import java.io.Serializable;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dokter")
public class DokterModel extends UserModel implements Serializable {
    @NotNull
    @Column(name = "tarif")
    private Integer tarif;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY)
    @JsonIgnore
    List<AppointmentModel> listAppointment;
}
