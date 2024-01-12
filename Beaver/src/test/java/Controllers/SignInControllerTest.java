package Controllers;

import Main.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * <p> The class SignInControllerTest is to test SignInController class.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 07/03/2021-13:12
 * @since 1.0
 */
public class SignInControllerTest extends ApplicationTest {
    /**
     * FXML loader to load SignUp page.
     */
    FXMLLoader loader = new FXMLLoader();
    /**
     * SignUpController instance.
     */
    SignInController signInController;


    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of SignInControllerTest.
     * @throws Exception Exception
     * @author Yuan DAI
     * @date 07/03/2021-13:17
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        loader.setLocation(getClass().getResource("/FXML/SignIn.fxml"));
        Main.scene = new Scene(loader.load());
        signInController =(SignInController) loader.getController();
        primaryStage.setScene(Main.scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Input verify test.
     * <p> Test the situations of verifying inputs, whether correct notifications can be displayed.
     *
     * @author Yuan DAI
     * @date 07/03/2021-14:02
     */
    @Test
    public void testInputVerify() {
        TextField textField = (TextField) Main.scene.lookup("#username");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        clickOn(textField);
        clickOn(passwordField);
        clickOn(textField);
        textField.setText("Danny123");
        clickOn(passwordField);
        passwordField.setText("!Aa112224");
        clickOn(textField);
        textField.setText("Danny");
        clickOn(passwordField);
        passwordField.setText("112233");
        clickOn(textField);
        clickOn(passwordField);
        passwordField.setText("!Aa112224");
        clickOn(textField);
    }

    /**
     * SignIn test.
     * <p> Test the situations of sign in, whether correct notifications can be displayed and whether it can sign in successfully.
     *
     * @author Yuan DAI
     * @date 07/03/2021-17:33
     */
    @Test
    public void testSignIn() {
        TextField textField = (TextField) Main.scene.lookup("#username");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        textField.setText("Danny");
        passwordField.setText("!Aa112224");
        clickOn(signInController.SignInBtn);
        Platform.runLater(() -> {
          signInController.connectWarning.setVisible(true);
        });
        clickOn(passwordField);
        passwordField.setText("!Aa11224");
        clickOn(signInController.SignInBtn);
        Platform.runLater(() -> {});
        clickOn(passwordField);
        passwordField.setText("!Aa112224");
        clickOn(signInController.SignInBtn);
    }

    /**
     * jumpToSignUp test.
     * <p> Test whether it can jump to sign up page when clicking the button.
     *
     * @author Yuan DAI
     * @date 07/03/2021-22:02
     */
    @Test
    public void testJumpToSignUp() {
        clickOn(signInController.SignUpBtn);
    }

    /**
     * jumpToSetPassword test.
     * <p> Test whether it can jump to set password page when clicking the button.
     *
     * @author Yuan DAI
     * @date 07/03/2021-22:03
     */
    @Test
    public void testJumpToSetPassword() {
        clickOn(signInController.SetPasswordBtn);
    }

    /**
     * jumpToMain test.
     * <p> Test whether it can jump to main menu page successfully.
     *
     * @author Yuan DAI
     * @date 07/03/2021-22:05
     */
    @Test
    public void testJumpToMain() {
        Platform.runLater(() -> {
            try {
                signInController.jumpToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * seeDataPolicy test.
     * <p> Test whether it can jump to data policy page successfully.
     *
     * @author Yuan DAI
     * @date 11/03/2021-21:30
     */
    @Test
    public void testSeeDataPolicy() {
        clickOn(signInController.DataPolicyBtn);
        Platform.runLater(() -> {});
    }

    /**
     * seeTerms test.
     * <p> Test whether it can jump to terms page successfully.
     *
     * @author Yuan DAI
     * @date 11/03/2021-21:31
     */
    @Test
    public void testSeeTerms() {
        clickOn(signInController.TermsBtn);
        Platform.runLater(() -> {});
    }

    /**
     * seeWebPage test.
     * <p> Test whether it can open website page successfully.
     *
     * @author Yuan DAI
     * @date 07/03/2021-21:32
     */
    @Test
    public void testSeeWebPage() {
        clickOn(signInController.WebBtn);
        Platform.runLater(() -> {});
    }
}
