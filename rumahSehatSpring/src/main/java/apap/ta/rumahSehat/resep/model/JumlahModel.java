package apap.ta.rumahSehat.resep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import apap.ta.rumahSehat.obat.model.ObatModel;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_jumlah", nullable = false)
  private Long idJumlah;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_obat", referencedColumnName = "id_obat", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ObatModel obat;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_resep", referencedColumnName = "id_resep", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ResepModel resep;

  @NotNull
  @Column(name = "kuantitas", nullable = false)
  private Integer kuantitas;
}
