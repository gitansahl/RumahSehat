package apap.ta.rumahSehat.tagihan.service;

import java.util.List;

import apap.ta.rumahSehat.appointment.model.AppointmentModel;
import apap.ta.rumahSehat.tagihan.model.TagihanModel;

public interface TagihanService {
  void addTagihan(TagihanModel tagihan);
  
  List<TagihanModel> getListTagihan();

  TagihanModel getTagihanById(Long id);

  TagihanModel addTagihanByDokter(AppointmentModel appointment);
}
