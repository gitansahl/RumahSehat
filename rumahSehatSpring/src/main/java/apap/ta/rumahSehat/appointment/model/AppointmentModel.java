package apap.ta.rumahSehat.appointment.model;

import apap.ta.rumahSehat.resep.ResepModel;
import apap.ta.rumahSehat.tagihan.TagihanModel;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.model.PasienModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "appointment")
public class AppointmentModel {

    @Id
    @Column(name = "kode_appointment")
    private String kodeAppointment;

    @NotNull
    @Column(name = "waktuAwal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

    @Column(name = "isDone", nullable = false)
    private Boolean isDone;

    //Relation with DokterModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    //Relation with PasienModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pasien", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    //Relation with TagihanModel
    @OneToOne(mappedBy = "kode_appointment", fetch = FetchType.LAZY)
    private TagihanModel tagihan;

    //Relation with ResepModel
    @OneToOne(mappedBy = "kode_appointment", fetch = FetchType.LAZY)
    private ResepModel resep;

}