package apap.ta.rumahSehat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApotekerDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
}
