package use_case.logout;

import use_case.logout.LogoutUserDataAccessInterface;

class MockLogoutUserDataAccess implements LogoutUserDataAccessInterface {

    private String currentUsername;

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        currentUsername = username;
    }
}