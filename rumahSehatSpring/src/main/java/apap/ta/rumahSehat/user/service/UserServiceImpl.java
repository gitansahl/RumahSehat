package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.authentication.setting.Setting;
import apap.ta.rumahSehat.user.repository.UserDb;
import apap.ta.rumahSehat.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDb userDb;

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public UserModel addUser(UserModel userModel) {
        userModel.setPassword(encrypt(userModel.getPassword()));
        return userDb.save(userModel);
    }

    @Override
    public boolean isWhitelist(String username) {
        if(Setting.whitelist.contains(username)) {
            return true;
        }
        return false;
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}