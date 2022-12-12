package apap.ta.rumahSehat.tagihan.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.appointment.repository.AppointmentDb;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.tagihan.repository.TagihanDb;
import apap.ta.rumahSehat.user.model.DokterModel;
import apap.ta.rumahSehat.user.model.PasienModel;
import apap.ta.rumahSehat.user.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService{

    @Autowired
    TagihanDb tagihanDb;

    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    PasienDb pasienDb;

    @Override
    public void addTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    @Override
    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public List<TagihanModel> getListTagihanPasien(String idPasien) {
        return tagihanDb.findAllByAppointment_Pasien_Id(idPasien);
    }

    @Override
    public boolean bayarTagihan(PasienModel pasien, TagihanModel tagihan) {
        Integer saldoPasien = pasien.getSaldo();
        Integer jumlahTagihan = tagihan.getJumlahTagihan();

        if (jumlahTagihan>saldoPasien){
            return false;
        }
        saldoPasien -= jumlahTagihan;
        pasien.setSaldo(saldoPasien);
        pasienDb.save(pasien);
        tagihan.setTanggalPembayaran(LocalDateTime.now());
        tagihan.setTerbayar(true);
        tagihanDb.save(tagihan);
        return true;

    }

    @Override
    public TagihanModel getTagihanByKodeTagihan(String kodeTagihan) {
        return tagihanDb.findByKodeTagihan(kodeTagihan);
    }

    @Override
    public TagihanModel getTagihanById(Long id) {
        return tagihanDb.findById(id).orElse(null);
    }

    @Override
    public TagihanModel addTagihanByDokter(AppointmentModel appointment) {
        DokterModel dokter = appointment.getDokter();
        Integer tarif = dokter.getTarif();
        TagihanModel tagihan = new TagihanModel();
        tagihan.setJumlahTagihan(tarif);
        tagihan.setAppointment(appointment);
        tagihan.setTanggalDibuat(LocalDateTime.now());
        tagihan.setTerbayar(false);
        tagihanDb.save(tagihan);
        tagihan.setKodeTagihan("BILL-" + tagihan.getId());
        tagihanDb.save(tagihan);
        return tagihan;
    }


}
