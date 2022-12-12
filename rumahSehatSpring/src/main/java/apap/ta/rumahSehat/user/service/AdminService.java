package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.AdminModel;

public interface AdminService {
  void addAdmin(AdminModel admin);

  AdminModel getAdminByUsername(String username);
}
