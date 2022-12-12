package apap.ta.rumahSehat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasienDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private Integer umur;
}
