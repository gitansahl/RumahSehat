package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import com.sun.istack.NotNull;
=======
import javax.validation.constraints.NotNull;
>>>>>>> ff45b65d2ff514f9c44128c7e5aa0e5b8993df7b
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
@Table(name = "pasien")
public class PasienModel extends UserModel implements Serializable {
    @NotNull
    @Column(name = "saldo")
    private Integer saldo = 0;

    @NotNull
    @Column(name = "umur")
    private Integer umur;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY)
    @JsonIgnore
    List<AppointmentModel> listAppointment;
}
