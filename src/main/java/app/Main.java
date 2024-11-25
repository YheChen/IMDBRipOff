package app;

import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        // Load .env file into system properties
        Dotenv.configure().systemProperties().load();

        // Build application
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addAccountView()
                .addWriteReviewView()
                .addBrowseView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addChangePasswordUseCase()
                .addWriteReviewUseCase()
                .addBrowseReviewUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
