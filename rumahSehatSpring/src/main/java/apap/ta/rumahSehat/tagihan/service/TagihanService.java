package apap.ta.rumahSehat.tagihan.service;

import java.util.List;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.user.model.PasienModel;

public interface TagihanService {
    void addTagihan(TagihanModel tagihan);

    List<TagihanModel> getListTagihan();

    TagihanModel getTagihanById(Long id);

    TagihanModel addTagihanByDokter(AppointmentModel appointment);

    List<TagihanModel> getListTagihanPasien(String idPasien);

    TagihanModel getTagihanByKodeTagihan(String kodeTagihan);

    boolean bayarTagihan(PasienModel pasien, TagihanModel tagihan);

}
