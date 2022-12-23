package apap.ta.rumahSehat.resep.dto;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.resep.model.JumlahModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResepDTOWeb {
    private AppointmentModel appointment;
    private List<JumlahModel> listJumlah;
}
