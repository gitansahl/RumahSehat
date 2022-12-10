package apap.ta.rumahSehat.statistic.dto;

import apap.ta.rumahSehat.user.model.DokterModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyLineChartRequestDTO {
    private YearMonth bulanTahun;
    private List<DokterModel> dokterModelList;
}
