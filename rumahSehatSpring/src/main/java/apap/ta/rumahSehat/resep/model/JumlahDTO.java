package apap.ta.rumahSehat.resep.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class JumlahDTO {
  private String obat;
  private Integer kuantitas;
  
  public JumlahDTO(String obat, Integer kuantitas){
      this.obat = obat;
      this.kuantitas = kuantitas;
  }
  
  public void setObat (String namaObat){
      this.obat = namaObat;
  }

  public void setKuantitas (Integer kuantitas){
    this.kuantitas = kuantitas;
  }
}

