package apap.ta.rumahSehat.statistic.dto;

import apap.ta.rumahSehat.user.model.DokterModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyLineChartRequestDTO {
    private int tahun = 2022;
    private List<DokterModel> dokterModelList;
}
