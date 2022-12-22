package apap.ta.rumahSehat.resep.model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResepDTO {
  private Long idResep;
  private String dokter;
  private String pasien;
  private String status;
  private String apoteker;
  private List<JumlahDTO> listObat;
  
  public ResepDTO(Long idResep, String dokter, String pasien, List<JumlahDTO> listObat){
      this.idResep = idResep;
      this.dokter = dokter;
      this.pasien = pasien;
      this.listObat = listObat;
  }

  public void setStatus (String status){
      this.status = status;
  }
  
  public void setApoteker (String namaApoteker){
      this.apoteker = namaApoteker;
  }    
}