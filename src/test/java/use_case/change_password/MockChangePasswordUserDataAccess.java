package use_case.change_password;

import entity.User;

class MockChangePasswordUserDataAccess implements ChangePasswordUserDataAccessInterface {

    private User lastChangedUser;

    @Override
    public void changePassword(User user) {
        lastChangedUser = user; // Simulate saving the user with the updated password
    }

    public User getLastChangedUser() {
        return lastChangedUser; // Return the user whose password was last changed
    }
}
