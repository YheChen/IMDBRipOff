package use_case.login;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

class TestLoginUserDataAccess implements LoginUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    private String currentUsername;

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
