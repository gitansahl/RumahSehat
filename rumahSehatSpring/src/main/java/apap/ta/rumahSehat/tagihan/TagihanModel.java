package apap.ta.rumahSehat.tagihan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tagihan")
public class TagihanModel {
    @Id
    @Column(name = "kode_tagihan")
    private String kodeTagihan;
}
