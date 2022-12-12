package apap.ta.rumahSehat.tagihan.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.tagihan.repository.TagihanDb;
import apap.ta.rumahSehat.user.model.DokterModel;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public void addTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    @Override
    public TagihanModel getTagihanById(Long id) {
        return tagihanDb.findById(id).orElse(null);
    }
    
    @Override
    public TagihanModel addTagihanByDokter(AppointmentModel appointment) {
        DokterModel dokter = appointment.getDokter();
        Long tarif = Long.valueOf(dokter.getTarif());
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
