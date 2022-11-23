package apap.ta.rumahSehat.resep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel confirmer;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jumlah", referencedColumnName = "id_jumlah")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JumlahModel jumlah;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode_appointment", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppointmentModel appointment;

    // @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private List<ObatModel> listObat;
}