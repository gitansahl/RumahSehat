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
@Table(name = "dokter")
public class DokterModel extends UserModel {
    @NotNull
    @Column(name = "tarif")
    private Integer tarif;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY)
    @JsonIgnore
    List<AppointmentModel> listAppointment;
}
