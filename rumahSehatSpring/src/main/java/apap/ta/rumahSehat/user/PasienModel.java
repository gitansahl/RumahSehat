package apap.ta.rumahSehat.user;

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
@Table(name = "pasien")
public class PasienModel extends UserModel{
    @NotNull
    @Column(name = "saldo")
    private Integer saldo = 0;

    @NotNull
    @Column(name = "umur")
    private Integer umur;

    @OneToMany
    List<AppointmentModel> listAppointment;
}
