package apap.ta.rumahSehat.tagihan.service;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;
import apap.ta.rumahSehat.user.model.PasienModel;

import java.util.List;

public interface TagihanService {

    void addTagihan(TagihanModel tagihan);

    List<TagihanModel> getListTagihanPasien(String idPasien);

    TagihanModel getTagihanByKodeTagihan(String kodeTagihan);

    List<TagihanModel> getListTagihan();

    TagihanModel getTagihanById(Long id);

    TagihanModel addTagihanByDokter(AppointmentModel appointment);

    boolean bayarTagihan(PasienModel pasien, TagihanModel tagihan);


}
