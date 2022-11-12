package apap.ta.rumahSehat.user.model;

import apap.ta.rumahSehat.appointment.AppointmentModel;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany
    List<AppointmentModel> listAppointment;
}
