package apap.ta.rumahSehat.user.service;

import apap.ta.rumahSehat.user.model.UserModel;

public interface UserService {
    UserModel getUserByUsername(String username);
    UserModel addUser(UserModel userModel);
    boolean isWhitelist(String username);
    String encrypt(String password);
}
