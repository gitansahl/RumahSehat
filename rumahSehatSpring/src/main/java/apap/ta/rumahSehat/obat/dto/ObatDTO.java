package apap.ta.rumahSehat.obat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObatDTO {
    private String idObat;
    private String namaObat;
    private int stok;
    private int harga;
}
