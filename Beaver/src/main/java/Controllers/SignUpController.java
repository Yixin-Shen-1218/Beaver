package Controllers;

import InputField.InputField;
import Main.Main;
import ToolPackage.JdbcUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * <p>The class SignUpController is used to control the sign up page
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 08/01/2021-14:29
 * @since 1.0
 */
public class SignUpController {
    /**
     * Sign up button
     */
    public Button SignUpBtn;
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
     * Text field for inputting username.
     */
    @FXML
    public TextField username;

    /**
     * Text field for inputting email address.
     */
    @FXML
    public TextField email;
    /**
     * Text field for inputting password.
     */
    @FXML
    public PasswordField password;
    /**
     * Text field to for inputting confirm password.
     */
    @FXML
    public PasswordField confirm;
    /**
     * Label for notice user about incorrect username format.
     */
    @FXML
    public Label unNotice;
    /**
     * Label for notice user about incorrect email format.
     */
    @FXML
    public Label eNotice;
    /**
     * Label for notice user about incorrect password format.
     */
    @FXML
    public Label pNotice;
    /**
     * Label for notice user about the error of confirming password.
     */
    @FXML
    public Label cpNotice;
    /**
     * Binding unNotice label to set text.
     */
    public StringProperty uText = new SimpleStringProperty();
    /**
     * Binding eNotice label to set text.
     */
    public StringProperty eText = new SimpleStringProperty();
    /**
     * Binding pNotice label to set text.
     */
    public StringProperty pText = new SimpleStringProperty();
    /**
     * Binding cpNotice label to set text.
     */
    public StringProperty cpText = new SimpleStringProperty();
    /**
     * String variable to receive text from 'FirstName' and 'LastName'
     */
    public String unText;
    /**
     * String variable to receive text from 'Password'
     */
    public String pwdText;
    /**
     * String variable to receive text from 'Email address'
     */
    public String emailText;
    /**
     * Input field object for user name
     */
    public InputField unField;
    /**
     * Input field object for email address
     */
    public InputField eField;
    /**
     * Input field object for password
     */
    public InputField pField;
    /**
     * Input field object for confirming password
     */
    public InputField cpField;
    /**
     * warning image
     */
    public Image image = new Image("/Image/SignIn_Up/warning.png");
    /**
     * string of sql statement
     */
    public String sqlState;
    /**
     * Preparing sql statement
     */
    public PreparedStatement pstmt;
    /**
     * listener for username textfield
     */
    public ChangeListener<Boolean> focusListener1 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(username, uText, unField);
            }
        }
    };
    /**
     * listener for email textfield
     */
    public ChangeListener<Boolean> focusListener2 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(email, eText, eField);
            }
        }
    };
    /**
     * listener for password textfield
     */
    public ChangeListener<Boolean> focusListener3 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(password, pText, pField);
            }
        }
    };
    /**
     * listener for confirm password textfield
     */
    public ChangeListener<Boolean> focusListener4 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(confirm, cpText, cpField);
            }
        }
    };

    /**
     * initialize the controller
     * <p> add listeners to relative text fields and bind relative labels to set notice information
     *
     * @author Yuan DAI
     * @date 13/01/2021-22:54
     */
    @FXML
    public void initialize() {
        this.warning.setImage(image);
        unField = new InputField("^[a-zA-Z]{2,15}$", "Username", true, false);
        eField = new InputField("^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", "Email address", true, false);
        pField = new InputField("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,16}$", "Password", true, false);
        cpField = new InputField("Confirm password", true, false);
        username.focusedProperty().addListener(focusListener1);
        unNotice.textProperty().bind(uText);
        email.focusedProperty().addListener(focusListener2);
        eNotice.textProperty().bind(eText);
        password.focusedProperty().addListener(focusListener3);
        pNotice.textProperty().bind(pText);
        confirm.focusedProperty().addListener(focusListener4);
        cpNotice.textProperty().bind(cpText);
    }


    /**
     * start sign up when clicking the button
     * <p> connect to database, verify the user information and then insert them to database if succeed when clicking the 'Sign In' button
     *
     * @throws ClassNotFoundException throws exception if cannot connect to the database
     * @throws SQLException           throws exception if sql operations failed
     * @throws IOException            IOException
     * @author Yuan DAI
     * @date 13/01/2021-17:40
     */
    @FXML
    public void signUp() throws SQLException, ClassNotFoundException, IOException {
        unText = username.getText();
        pwdText = password.getText();
        emailText = email.getText();
        if (unField.getIsInvalid() || eField.getIsInvalid() || pField.getIsInvalid() || cpField.getIsInvalid()) {
            return;
        }
        if (JdbcUtil.IsConnectDatabase()) {
            connectWarning.setVisible(true);
            return;
        } else {
            connectWarning.setVisible(false);
        }

        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select Username from userInform where Username like '" + username.getText() + "'");
        if (JdbcUtil.rs.next()) {
            uText.set("The username has exist!");
            if (!unField.getIsAdded()) {
                username.getStyleClass().add("textField2-error");
                unField.setIsAdded(true);
            }
            return;
        }

        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select Email from userInform where Email = '" + emailText + "'");
        if (JdbcUtil.rs.next()) {
            eText.set("The email address has exist!");
            if (!eField.getIsAdded()) {
                email.getStyleClass().add("textField2-error");
                eField.setIsAdded(true);
            }
            return;
        }
        sqlState = "insert into userInform (Username, Password, Email) values (?,?,?)";
        pstmt = JdbcUtil.con.prepareStatement(sqlState);
        pstmt.setString(1, unText);
        pstmt.setString(2, pwdText);
        pstmt.setString(3, emailText);
        pstmt.executeUpdate();
        JdbcUtil.closeConnect();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(SignUpBtn.getScene().getWindow());
        alert.setTitle("Successful Registration");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! Your registration is successful.");
        alert.showAndWait();

        jumpToSignIn();
    }

    /**
     * Verifying if input satisfied the format requirement
     * <p> If not, give error information
     *
     * @param input       relative text field
     * @param noticeField relative field for noticing user
     * @param field       relative input field object
     * @author Yuan DAI
     * @date 18/01/2021-16:05
     */
    @FXML
    public void inputVerify(TextField input, StringProperty noticeField, InputField field) {
        String inputText = input.getText();
        if (field.getInputType().equals("Confirm password")) {
            String passwordVerify = password.getText();
            if (inputText.equals("")) {
                noticeField.set(field.getInputType() + " is empty!");
                field.setIsInvalid(true);
                if (!field.getIsAdded()) {
                    input.getStyleClass().add("textField2-error");
                    field.setIsAdded(true);
                }
            } else if (!passwordVerify.equals(inputText)) {
                noticeField.set("Confirm password is different with password!");
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
            return;
        }
        if (inputText.equals("")) {
            noticeField.set(field.getInputType() + " is empty!");
            field.setIsInvalid(true);
            if (!field.getIsAdded()) {
                input.getStyleClass().add("textField2-error");
                field.setIsAdded(true);
            }
        } else if (!inputText.matches(field.getRegex())) {
            noticeField.set(field.getInputType() + " is incorrect!");
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
     * jump to sign in page
     * <p> invoke the method in Main to jump to sign in page
     *
     * @throws IOException IOException
     * @author Yuan DAI
     * @date 13/11/2021-18:22
     */
    @FXML
    public void jumpToSignIn() throws IOException {
        Main.setRoot("/FXML/SignIn");
    }


}
