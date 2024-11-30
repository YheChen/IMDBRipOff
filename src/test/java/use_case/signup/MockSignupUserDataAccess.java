package use_case.signup;

import entity.User;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

class MockSignupUserDataAccess implements SignupUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null"); // Add this check for debugging
        }
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
