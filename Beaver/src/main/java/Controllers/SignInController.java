package Controllers;

import InputField.InputField;
import Main.Main;
import ToolPackage.JdbcUtil;
import TreeControl.TreeItemCollection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * <p>The class SignInController is used to control the sign in page
 *
 * @author Yixin SHEN
 * @version 1.0
 * @since 1.0
 */
public class SignInController {
    /**
     * Sign in button.
     */
    public Button SignInBtn;
    /**
     * Sign up button.
     */
    public Button SignUpBtn;
    /**
     * Set password button.
     */
    public Button SetPasswordBtn;

    /**
     * Terms button.
     */
    public Button TermsBtn;

    /**
     * Data policy button.
     */
    public Button DataPolicyBtn;

    /**
     * About us button.
     */
    public Button WebBtn;
    /**
     * display connect warning message
     */
    @FXML
    public AnchorPane connectWarning;
    /**
     * displaying warning png
     */
    @FXML
    public ImageView warning;
    /**
     * Text field for inputting username or email address.
     */
    @FXML
    public TextField username;
    /**
     * Text field for inputting password.
     */
    @FXML
    public PasswordField password;
    /**
     * Label for notice user about incorrect user inform.
     */
    @FXML
    public Label ueNotice;
    /**
     * Label for notice user about incorrect password.
     */
    @FXML
    public Label pNotice;
    /**
     * Binding neNotice label to set text.
     */
    public StringProperty ueText = new SimpleStringProperty();
    /**
     * Binding pNotice label to set text.
     */
    public StringProperty pText = new SimpleStringProperty();
    /**
     * Input field object for password
     */
    public InputField pField;
    /**
     * Input field object for username and email
     */
    public InputField ueField;
    /**
     * String variable to receive text from 'Username'
     */
    public String uneText;
    /**
     * String variable to receive text from 'Password'
     */
    public String pwdText;
    /**
     * warning image
     */
    public Image image;
    /**
     * listener for username and email address textfield
     */
    public ChangeListener<Boolean> focusListener1 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(username, ueText, ueField);
            }
        }
    };
    /**
     * listener for password textfield
     */
    public ChangeListener<Boolean> focusListener2 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(password, pText, pField);
            }
        }
    };

    /**
     * initialize the controller
     * <p> add listeners to relative text fields and bind relative labels to set notice information
     *
     * @author Yichen ZHANG
     * @date 20/01/2021-20:28
     */
    @FXML
    public void initialize() {
        image = new Image("/Image/SignIn_Up/warning.png");
        this.warning.setImage(image);
        ueField = new InputField("^[a-zA-Z]{2,15}$", "^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", "Username or Email address", true, false);
        pField = new InputField("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,16}$", "Password", true, false);
        username.focusedProperty().addListener(focusListener1);
        password.focusedProperty().addListener(focusListener2);
        ueNotice.textProperty().bind(ueText);
        pNotice.textProperty().bind(pText);
    }

    /**
     * start sign in when clicking the button
     * <p> connect to database, verify the user information and then turn to main menu if succeed when clicking the 'Sign In' button
     *
     * @throws ClassNotFoundException throws exception if cannot connect to the database
     * @throws SQLException           throws exception if sql operations failed
     * @throws IOException            IOException
     * @author Yichen ZHANG
     * @date 12/01/2021-15:08
     */
    @FXML
    public void signIn() throws SQLException, ClassNotFoundException, IOException {

        uneText = username.getText();
        pwdText = password.getText();

        if (ueField.getIsInvalid() || pField.getIsInvalid()) {
            return;
        }

        if (JdbcUtil.IsConnectDatabase()) {
            connectWarning.setVisible(true);
            return;
        } else {
            connectWarning.setVisible(false);
        }
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select * from userInform");
        //traverse the result set
        while (JdbcUtil.rs.next()) {
            if (((JdbcUtil.rs.getString("Username").equals(uneText)) || (JdbcUtil.rs.getString("Email").equals(uneText))) && (JdbcUtil.rs.getString("Password").equals(pwdText))) {
                System.out.println(uneText);
                Main.username = uneText;
                Main.UID = JdbcUtil.rs.getString("UID");

                // Declare treeItem collection
                MainMenuController.treeItemCollection = new TreeItemCollection();

                // jump to main screen
                jumpToMain();
                //close the interfaces
                JdbcUtil.closeConnect();
                return;
            }
        }

        System.out.println("error");
        ueText.set("User does not exist or password is incorrect!");
        if (!ueField.getIsAdded()) {
            username.getStyleClass().add("textField2-error");
            ueField.setIsAdded(true);
        }
        if (!pField.getIsAdded()) {
            password.getStyleClass().add("textField2-error");
            pField.setIsAdded(true);
        }


        JdbcUtil.closeConnect();
        return;

    }


    /**
     * Verifying if input satisfied the format requirement
     * <p> If not, give error information
     *
     * @param input       relative text field
     * @param noticeField relative field for noticing user
     * @param field       relative input field object
     * @author Yichen ZHANG
     * @date 18/01/2021-16:05
     */
    @FXML
    public void inputVerify(TextField input, StringProperty noticeField, InputField field) {
        String inputText = input.getText();
        if (inputText.equals("")) {
            noticeField.set(field.getInputType() + " is empty!");
            field.setIsInvalid(true);
            if (!field.getIsAdded()) {
                input.getStyleClass().add("textField2-error");
                field.setIsAdded(true);
            }
        } else if (!inputText.matches(field.getRegex()) && !inputText.matches(field.getRegex2())) {
            noticeField.set(field.getInputType() + " format is incorrect!");
            field.setIsInvalid(true);
            if (!field.getIsAdded()) {
                input.getStyleClass().add("textField2-error");
                field.setIsAdded(true);
            }
        } else {
            if (field.getIsAdded()) {
                input.getStyleClass().remove("textField2-error");
                field.setIsAdded(false);
            }
            noticeField.set("");
            field.setIsInvalid(false);
        }
    }


    /**
     * jump to main menu
     * <p> invoke the method in Main to jump to main menu
     *
     * @throws IOException IOException
     * @author Yichen ZHANG
     * @date 12/01/2021-16:17
     */
    public void jumpToMain() throws IOException {
        Main.toMainMenu();
    }


    /**
     * jump to sign up page
     * <p> invoke the method in Main to jump to sign up page
     *
     * @throws IOException IOException
     * @author Yichen ZHANG
     * @date 12/01/2021-17:33
     */
    @FXML
    public void jumpToSignUp() throws IOException {
        Main.setRoot("/FXML/SignUp");
    }

    /**
     * jump to set password page
     * <p> invoke the method in Main to jump to set password page
     *
     * @throws IOException IOException
     * @author Yichen ZHANG
     * @date 12/01/2021-17:33
     */
    @FXML
    public void jumpToSetPassword() throws IOException {
        Main.setRoot("/FXML/SetPassword");
    }

    /**
     * Jump to data policy page.
     * <p> Jump to data policy page.
     *
     * @throws IOException IOException
     * @author Yichen ZHANG
     * @date 08/03/2021-17:33
     */
    @FXML
    public void seeDataPolicy() throws IOException {
        Stage dataPolicyStage = new Stage();
        dataPolicyStage.setTitle("Data Policy");
        Scene dataPolicyScene = new Scene(Main.loadFXML("/FXML/DataPolicy"));
        dataPolicyStage.setScene(dataPolicyScene);
        Main.setlogo(dataPolicyStage);
        dataPolicyStage.setResizable(false);
        dataPolicyStage.show();
    }

    /**
     * Jump to terms page.
     * <p> Jump to terms page.
     *
     * @throws IOException IOException
     * @author Yichen ZHANG
     * @date 08/03/2021-17:33
     */
    @FXML
    public void seeTerms() throws IOException {
        Stage termsStage = new Stage();
        termsStage.setTitle("Terms");
        Scene termsScene = new Scene(Main.loadFXML("/FXML/Terms"));
        termsStage.setScene(termsScene);
        Main.setlogo(termsStage);
        termsStage.setResizable(false);
        termsStage.show();
    }

    /**
     * Open website page.
     * <p> Open website page.
     *
     * @throws IOException IOException
     * @throws URISyntaxException URISyntaxException
     * @author Yichen ZHANG
     * @date 08/03/2021-17:33
     */
    @FXML
    public void seeWebPage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://cslinux.nottingham.edu.cn/~Team202001/"));
    }
}

