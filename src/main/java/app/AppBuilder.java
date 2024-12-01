package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryReviewDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.account.AccountViewModel;
import interface_adapter.browse_review.BrowseReviewController;
import interface_adapter.browse_review.BrowseReviewPresenter;
import interface_adapter.browse_review.BrowseReviewViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewPresenter;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.account.AccountInputBoundary;
import use_case.account.AccountInteractor;
import use_case.browse_reviews.BrowseReviewInputBoundary;
import use_case.browse_reviews.BrowseReviewInteractor;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInteractor;
import view.AccountView;
import view.BrowseView;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;
import view.WriteReviewView;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new entity.CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final InMemoryReviewDataAccessObject reviewDataAccessObject = new InMemoryReviewDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private BrowseView browseView;
    private BrowseReviewViewModel browseReviewViewModel;
    private WriteReviewView writeReviewView;
    private WriteReviewViewModel writeReviewViewModel;
    private AccountViewModel accountViewModel;
    private AccountView accountView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Account View to the application.
     * @return this builder
     */
    public AppBuilder addAccountView() {
        accountViewModel = new AccountViewModel();
        accountView = new AccountView(accountViewModel);
        cardPanel.add(accountView, accountView.getViewName());
        return this;
    }

    /**
     * Adds the WriteReview View to the application.
     * @return this builder
     * @throws Exception throws an exception if an api call fails
     */
    public AppBuilder addWriteReviewView() throws Exception {
        writeReviewViewModel = new WriteReviewViewModel();
        writeReviewView = new WriteReviewView(writeReviewViewModel);
        System.out.println(writeReviewView.getViewName());
        cardPanel.add(writeReviewView, writeReviewView.getViewName());
        return this;
    }

    /**
     * Adds the BrowseReview View to the application.
     * @return this builder
     * @throws Exception throws an exception if an api call fails
     */
    public AppBuilder addBrowseView() throws Exception {
        browseReviewViewModel = new BrowseReviewViewModel();
        browseView = new BrowseView(browseReviewViewModel);
        // System.out.println(browseView.getViewName());
        cardPanel.add(browseView, browseView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                accountViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(accountViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        accountView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                accountViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        accountView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Write Review Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWriteReviewUseCase() {
        final WriteReviewPresenter writeReviewOutputBoundary = new WriteReviewPresenter(browseReviewViewModel,
                accountViewModel, writeReviewViewModel, viewManagerModel);

        final WriteReviewInputBoundary writeReviewInteractor =
                new WriteReviewInteractor(reviewDataAccessObject, writeReviewOutputBoundary);

        final WriteReviewController writeReviewController = new WriteReviewController(writeReviewInteractor);
        writeReviewView.setWriteReviewController(writeReviewController);
        return this;
    }

    /**
     * Adds the Browse Review Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBrowseReviewUseCase() {
        final BrowseReviewPresenter browseReviewOutputBoundary = new BrowseReviewPresenter(browseReviewViewModel,
                writeReviewViewModel, accountViewModel, viewManagerModel);
        final BrowseReviewInputBoundary browseReviewInteractor =
                new BrowseReviewInteractor(browseReviewOutputBoundary);

        final BrowseReviewController browseReviewController = new BrowseReviewController(browseReviewInteractor);
        browseView.setBrowseController(browseReviewController);
        return this;
    }

    /**
     * Adds button use case to this application.
     * @return this builder
     */
    public AppBuilder addButtonUseCase() {
        final AccountPresenter accountOutputBoundary = new AccountPresenter(browseReviewViewModel,
                writeReviewViewModel, accountViewModel, viewManagerModel);
        final AccountInputBoundary accountInteractor =
                new AccountInteractor(accountOutputBoundary);

        final AccountController accountController = new AccountController(accountInteractor);
        accountView.setAccountController(accountController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the BrowseView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Movie Reviewer 1000");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        viewManagerModel.setState(accountViewModel.getViewName());
        System.out.println(browseReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
