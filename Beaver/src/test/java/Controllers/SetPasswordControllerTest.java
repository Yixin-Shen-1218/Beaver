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

import java.awt.*;
import java.io.IOException;

/**
 * <p> The class SetPasswordControllerTest is to test SetPasswordController class.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 08/03/2021-21:22
 * @since 1.0
 */
public class SetPasswordControllerTest extends ApplicationTest {
    /**
     * FXML loader to load SignUp page.
     */
    FXMLLoader loader = new FXMLLoader();
    /**
     * SignUpController instance.
     */
    SetPasswordController setPasswordController;

    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of SetPasswordControllerTest.
     * @throws Exception Exception
     * @author Yuan DAI
     * @date 08/03/2021-21:39
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        loader.setLocation(getClass().getResource("/FXML/SetPassword.fxml"));
        Main.scene = new Scene(loader.load());
        setPasswordController =(SetPasswordController) loader.getController();
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
     * @date 08/03/2021-22:30
     */
    @Test
    public void testInputVerify() {
        TextField emailField = (TextField) Main.scene.lookup("#email");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");
        clickOn(emailField);
        clickOn(passwordField);
        clickOn(confirmField);
        clickOn(emailField);
        emailField.setText("123456@cloud");
        clickOn(passwordField);
        passwordField.setText("!123qweq");
        clickOn(emailField);
        emailField.setText("123456@cloud.com");
        clickOn(passwordField);
        passwordField.setText("!123qweQWE");
        clickOn(confirmField);
        confirmField.setText("!223qweQWE");
        clickOn(passwordField);
        clickOn(confirmField);
        confirmField.setText("!123qweQWE");
        clickOn(passwordField);
    }

    /**
     * Send code test.
     * <p> Test the situations of send code, whether correct notifications can be displayed and whether it can send code successfully.
     *
     * @author Yuan DAI
     * @date 08/03/2021-22:54
     */
    @Test
    public void testSendCode() {
        TextField emailField = (TextField) Main.scene.lookup("#email");
        TextField codeField = (TextField) Main.scene.lookup("#veriCode");
        clickOn(emailField);
        emailField.setText("333@cloud");
        clickOn(setPasswordController.sendBtn);
        Platform.runLater(() -> {
            setPasswordController.connectWarning.setVisible(true);
        });
        clickOn(emailField);
        emailField.setText("26925213@qq.com");
        clickOn(codeField);
        clickOn(setPasswordController.sendBtn);
        Platform.runLater(() -> {});
        clickOn(emailField);
        emailField.setText("2692521364@qq.com");
        clickOn(codeField);
        clickOn(setPasswordController.sendBtn);
        Platform.runLater(() -> {});
        clickOn(emailField);
        clickOn(setPasswordController.sendBtn);
    }

    /**
     * Create new password test.
     * <p> Test the situations of reset password, whether correct notifications can be displayed and whether it can reset password successfully.
     *
     * @author Yuan DAI
     * @date 07/03/2021-23:28
     */
    @Test
    public void testCreatePassword() {
        TextField emailField = (TextField) Main.scene.lookup("#email");
        TextField codeField = (TextField) Main.scene.lookup("#veriCode");
        PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");
        PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");
        clickOn(emailField);
        emailField.setText("2692521364@qq.com");
        clickOn(codeField);
        codeField.setText("123ASD");
        clickOn(passwordField);
        passwordField.setText("!123qweQWE");
        clickOn(confirmField);
        confirmField.setText("!123qweQWE");
        clickOn(setPasswordController.SetPasswordBtn);
        clickOn(setPasswordController.sendBtn);
        Platform.runLater(() -> {});
        clickOn(codeField);
        codeField.setText(setPasswordController.code.toString());
        clickOn(passwordField);
        passwordField.setText("!123qweQWE");
        clickOn(confirmField);
        confirmField.setText("!123qweQWEr");
        clickOn(setPasswordController.SetPasswordBtn);
        Platform.runLater(() -> {});
        clickOn(confirmField);
        confirmField.setText("!123qweQWE");
        clickOn(emailField);
        emailField.setText("123@cloud.com");
        clickOn(setPasswordController.SetPasswordBtn);
        Platform.runLater(() -> {});
        clickOn(emailField);
        emailField.setText("2692521364@qq.com");
        clickOn(codeField);
        codeField.setText("");
        clickOn(passwordField);
        clickOn(setPasswordController.SetPasswordBtn);
        Platform.runLater(() -> {});
        clickOn(codeField);
        codeField.setText("123ASD");
        clickOn(passwordField);
        clickOn(setPasswordController.SetPasswordBtn);
        Platform.runLater(() -> {});
        clickOn(codeField);
        codeField.setText(setPasswordController.code.toString());
        clickOn(passwordField);
        clickOn(setPasswordController.SetPasswordBtn);
        Platform.runLater(() -> {});
    }

    /**
     * jumpToSignIn test.
     * <p> Test whether it can jump to sign in page successfully.
     *
     * @author Yuan DAI
     * @date 08/03/2021-23:49
     */
    @Test
    public void testJumpToSignIn() {
        Platform.runLater(() -> {
            try {
                setPasswordController.jumpToSignIn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
