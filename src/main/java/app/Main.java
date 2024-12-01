package app;

import javax.swing.JFrame;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     * @throws Exception throws an exception if an api call fails
     */
    public static void main(String[] args) throws Exception {
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
                .addButtonUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
