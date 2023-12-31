package apap.ta.rumahSehat.obat.model;

import apap.ta.rumahSehat.resep.model.JumlahModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Column(name = "stok", nullable = false, columnDefinition = "integer default 100")
    private int stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private int harga;

    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JumlahModel> listjumlah;
}
