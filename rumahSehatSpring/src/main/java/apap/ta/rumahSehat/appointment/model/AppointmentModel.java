package apap.ta.rumahSehat.appointment.model;

import apap.ta.rumahSehat.resep.model.ResepModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idAppointment;

    @Column(name = "kode_appointment")
    private String kodeAppointment;

    @NotNull
    @Column(name = "waktuAwal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "isDone", nullable = false)
    private Boolean isDone;

    //Relation with DokterModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"username", "email", "tarif"})
    private DokterModel dokter;

    //Relation with PasienModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pasien", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PasienModel pasien;

    //Relation with TagihanModel
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private TagihanModel tagihan;

    //Relation with ResepModel
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resep", referencedColumnName = "id_resep")
    @JsonIgnoreProperties(value = {"confirmer", "listJumlah", "appointment", "isDone", "createdAt"})
    private ResepModel resep;

}
