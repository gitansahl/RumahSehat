package apap.ta.rumahSehat.resep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.obat.model.ObatModel;
import apap.ta.rumahSehat.user.model.ApotekerModel;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resep")
public class ResepModel implements Serializable {
    @Id
    @Column(name = "id_resep")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idResep;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional =  false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel confirmer;

    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JumlahModel> listJumlah;


    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private AppointmentModel appointment;

    // @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //kayanya ini gaperlu dah
    // private List<ObatModel> listObat;
}