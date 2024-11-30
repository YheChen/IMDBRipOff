package use_case.signup;

class MockSignupOutputBoundary implements SignupOutputBoundary {

    private SignupOutputData successData;
    private String errorMessage;
    private boolean switchedToLoginView = false;

    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        this.successData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void switchToLoginView() {
        this.switchedToLoginView = true;
    }

    public SignupOutputData getSuccessData() {
        return successData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSwitchedToLoginView() {
        return switchedToLoginView;
    }
}
