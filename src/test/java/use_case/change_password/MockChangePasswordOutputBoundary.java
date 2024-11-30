package use_case.change_password;

class MockChangePasswordOutputBoundary implements ChangePasswordOutputBoundary {

    private ChangePasswordOutputData successOutputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        this.successOutputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ChangePasswordOutputData getSuccessOutputData() {
        return successOutputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
