package apap.ta.rumahSehat.user;

public interface UserService {
    UserModel getUserByUsername(String username);
    UserModel addUser(UserModel userModel);
    boolean isWhitelist(String username);
    String encrypt(String password);
}
