package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
@Table(name = "pasien")
public class PasienModel extends UserModel {
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
