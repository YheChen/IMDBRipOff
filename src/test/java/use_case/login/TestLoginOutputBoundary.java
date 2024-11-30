package use_case.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

class TestLoginOutputBoundary implements LoginOutputBoundary {

    private LoginOutputData successOutput;
    private String failureMessage;

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        this.successOutput = outputData;
        this.failureMessage = null; // Clear any previous failure message
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failureMessage = errorMessage;
        this.successOutput = null; // Clear any previous success output
    }

    public LoginOutputData getSuccessOutput() {
        return successOutput;
    }

    public String getFailureMessage() {
        return failureMessage;
    }
}
