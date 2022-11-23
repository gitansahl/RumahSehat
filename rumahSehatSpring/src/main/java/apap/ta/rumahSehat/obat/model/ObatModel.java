package apap.ta.rumahSehat.obat.model;

import apap.ta.rumahSehat.resep.model.JumlahModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "obat")
public class ObatModel {
    @Id
    @Column(name = "id_obat")
    private String idObat;

    @NotNull
    @Column(name = "nama_obat", nullable = false)
    private String namaObat;

    @NotNull
    @Column(name = "stok",columnDefinition = "integer default 100")
    private int stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private int harga;


//    @OneToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "jumlah", referencedColumnName = "id_jumlah")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private JumlahModel jumlah;






}
