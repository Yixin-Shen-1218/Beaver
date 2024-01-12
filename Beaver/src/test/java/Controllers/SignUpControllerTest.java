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

/**
 * <p> The class SignUpControllerTest is to test SignUpController class.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 08/03/2021-19:03
 * @since 1.0
 */
public class SignUpControllerTest extends ApplicationTest {

    /**
     * FXML loader to load SignUp page.
     */
    FXMLLoader loader = new FXMLLoader();
    /**
     * SignUpController instance.
     */
    SignUpController signUpController;

    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of SignUpControllerTest.
     * @throws Exception Exception
     * @author Yuan DAI
     * @date 08/03/2021-19:09
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        loader.setLocation(getClass().getResource("/FXML/SignUp.fxml"));
        Main.scene = new Scene(loader.load());
        signUpController =(SignUpController) loader.getController();
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
     * @date 07/03/2021-19:10
     */
    @Test
    public void testInputVerify() {
        TextField textField = (TextField) Main.scene.lookup("#username");
        TextField emailField = (TextField) Main.scene.lookup("#email");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");
        clickOn(textField);
        clickOn(emailField);
        clickOn(passwordField);
        clickOn(confirmField);
        clickOn(textField);
        textField.setText("Daniel123");
        clickOn(emailField);
        emailField.setText("123123@qq");
        clickOn(passwordField);
        passwordField.setText("!123qweqwe");
        clickOn(textField);
        textField.setText("Daniel");
        clickOn(emailField);
        emailField.setText("123123@qq.com");
        clickOn(passwordField);
        passwordField.setText("!123qweQWE");
        clickOn(confirmField);
        confirmField.setText("!123qweRTY");
        clickOn(passwordField);
        clickOn(confirmField);
        confirmField.setText("!123qweQWE");
        clickOn(passwordField);
    }

    /**
     * SignUp test.
     * <p> Test the situations of sign up, whether correct notifications can be displayed and whether it can sign up successfully.
     *
     * @author Yuan DAI
     * @date 08/03/2021-17:34
     */
    @Test
    public void testSignUp() {
        TextField textField = (TextField) Main.scene.lookup("#username");
        TextField emailField = (TextField) Main.scene.lookup("#email");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");
        clickOn(textField);
        textField.setText("Dave");
        clickOn(emailField);
        emailField.setText("456567cc@qq.com");
        clickOn(passwordField);
        passwordField.setText("!123qweQWE");
        clickOn(confirmField);
        confirmField.setText("!123qweQWE");
        clickOn(signUpController.SignUpBtn);
        Platform.runLater(() -> {
            signUpController.connectWarning.setVisible(true);
        });
        clickOn(textField);
        textField.setText("Danny");
        clickOn(signUpController.SignUpBtn);
       Platform.runLater(() -> {
       });
        clickOn(textField);
        textField.setText("Dave");
        clickOn(emailField);
        emailField.setText("2692521364@qq.com");
        clickOn(signUpController.SignUpBtn);
        Platform.runLater(() -> {
        });
        clickOn(emailField);
        emailField.setText("456567cc@qq.com");
        clickOn(signUpController.SignUpBtn);
    }

    /**
     * jumpToSignIn test.
     * <p> Test whether it can jump to sign in page successfully.
     *
     * @author Yuan DAI
     * @date 08/03/2021-21:13
     */
    @Test
    public void testJumpToSignIn() {
        Platform.runLater(() -> {
            try {
                signUpController.jumpToSignIn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
