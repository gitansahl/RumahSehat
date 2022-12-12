package apap.ta.rumahSehat.statistic;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.statistic.dto.BarChartRequestDTO;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChartService {

    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    DokterDb dokterDb;

    int[] getDataPendapatanDokter(List<AppointmentModel> appointmentModelList) {
        var res = new int[12];

        for (AppointmentModel appointment : appointmentModelList) {
            res[appointment.getWaktuAwal().getMonthValue()-1] += appointment.getDokter().getTarif();
        }

        return res;
    }

    List<int[]> getDataLineChartMonthly(List<DokterModel> dokterModelList, int year) {
        List<int[]> res = new ArrayList<>();

        var awal = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime akhir = awal.plusYears(1).minusMinutes(1);

        for (var i=0; i<dokterModelList.size(); i++) {
            var data = new int[12];

            var dokterModel = dokterDb.findByUsername(dokterModelList.get(i).getUsername());

            List<AppointmentModel> appointmentModelList = appointmentDb
                    .findAllByDokter_UsernameAndWaktuAwalBetween(
                            dokterModel.getUsername(),
                            awal,
                            akhir
                    );

            for (var j=0; j<appointmentModelList.size(); j++) {
                data[appointmentModelList.get(j).getWaktuAwal().getMonthValue()-1] += dokterModel.getTarif();
            }
            res.add(data);
        }
        return res;
    }

    List<int[]> getDataLineChartDaily(List<DokterModel> dokterModelList, int year, int month) {
        List<int[]> res = new ArrayList<>();

        var awal = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime akhir = awal.plusMonths(1).minusMinutes(1);

        for (var i=0; i<dokterModelList.size(); i++) {
            var data = new int[akhir.getDayOfMonth()];

            var dokterModel = dokterModelList.get(i);

            List<AppointmentModel> appointmentModelList = appointmentDb
                    .findAllByDokter_UsernameAndWaktuAwalBetween(
                            dokterModel.getUsername(),
                            awal,
                            akhir
            );

            for (var j=0; j<appointmentModelList.size(); j++) {
                data[appointmentModelList.get(j).getWaktuAwal().getDayOfMonth()-1] += dokterModel.getTarif();
            }
            res.add(data);
        }

        return res;
    }

    int[] getBarChartData(BarChartRequestDTO barChartRequestDTO) {
        var data = new int[barChartRequestDTO.getDokterModelList().size()];
        List<DokterModel> dokterModelList = barChartRequestDTO.getDokterModelList();

        for (var i=0; i<data.length; i++) {
            List<AppointmentModel> appointmentModelList = appointmentDb.findAllByDokter_Username(dokterModelList.get(i).getUsername());

            data[i] = appointmentModelList.size();

            if (barChartRequestDTO.getTipe().equals("Pendapatan")) {
                var dokterModel = dokterDb.findByUsername(dokterModelList.get(i).getUsername());
                data[i] *= dokterModel.getTarif();
            }
        }
        return data;
    }
}
