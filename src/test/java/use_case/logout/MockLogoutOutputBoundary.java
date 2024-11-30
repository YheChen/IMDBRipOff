package use_case.logout;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

class MockLogoutOutputBoundary implements LogoutOutputBoundary {

    private LogoutOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        this.outputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LogoutOutputData getOutputData() {
        return outputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}