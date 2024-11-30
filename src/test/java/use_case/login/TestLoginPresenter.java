package use_case.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

class TestLoginPresenter implements LoginOutputBoundary {
    private LoginOutputData outputData;
    private String failureMessage;

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        this.outputData = loginOutputData;
    }

    @Override
    public void prepareFailView(String message) {
        this.failureMessage = message;
    }

    public LoginOutputData getOutputData() {
        return outputData;
    }

    public String getFailureMessage() {
        return failureMessage;
    }
}
