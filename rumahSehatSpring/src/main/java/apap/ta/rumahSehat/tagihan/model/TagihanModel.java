package apap.ta.rumahSehat.tagihan.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;


@Entity
@Data
@Table(name = "tagihan")
public class TagihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kode_tagihan")
    private String kodeTagihan;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_pembayaran")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalPembayaran;

    @NotNull
    @Column(name = "terbayar", nullable = false)
    private Boolean terbayar;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private Long jumlahTagihan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_appointment", referencedColumnName = "id_appointment")
    private AppointmentModel appointment;
}
