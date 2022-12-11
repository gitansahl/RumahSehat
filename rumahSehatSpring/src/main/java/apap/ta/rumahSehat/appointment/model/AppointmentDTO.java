package apap.ta.rumahSehat.appointment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO implements Serializable {
    @JsonProperty("waktu_awal")
    private LocalDateTime waktuAwal;

    @JsonProperty("id_dokter")
    private String usernameDokter;
}
