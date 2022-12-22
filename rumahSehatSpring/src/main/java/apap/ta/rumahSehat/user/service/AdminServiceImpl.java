package apap.ta.rumahSehat.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.ta.rumahSehat.user.model.AdminModel;
import apap.ta.rumahSehat.user.repository.AdminDb;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
  @Autowired
  private AdminDb adminDb;

  @Override
  public void addAdmin(AdminModel admin) {
      String encryptedPass = encrypt(admin.getPassword());
      admin.setPassword(encryptedPass);
      adminDb.save(admin);

  }

  public String encrypt(String password) {
      var passwordEncoder = new BCryptPasswordEncoder();
      return passwordEncoder.encode(password);
  }

  @Override
  public AdminModel getAdminByUsername(String username){
      return adminDb.findByUsername(username);
  }
}
