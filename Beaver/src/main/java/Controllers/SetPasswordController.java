package Controllers;

import InputField.InputField;
import Main.Main;
import ToolPackage.JdbcUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

/**
 * <p> The class SetPasswordController is used to control the set password page
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 19/01/2021-22:48
 * @since 1.0
 */
public class SetPasswordController {
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
     * The button for sending email.
     */
    @FXML
    public Button sendBtn;
    /**
     * Create new password button.
     */
    @FXML
    public Button SetPasswordBtn;
    /**
     * Text field for inputting email address.
     */
    @FXML
    public TextField email;
    /**
     * Text field for inputting verification code.
     */
    @FXML
    public TextField veriCode;
    /**
     * Password field for inputting new password.
     */
    @FXML
    public PasswordField password;
    /**
     * Password field for inputting confirm password.
     */
    @FXML
    public PasswordField confirm;
    /**
     * Label for notice user about incorrect email address input.
     */
    @FXML
    public Label eNotice;
    /**
     * Label for notice user about incorrect verification code.
     */
    @FXML
    public Label codeNotice;
    /**
     * Label for notice user about incorrect new password format.
     */
    @FXML
    public Label pNotice;
    /**
     * Label for notice user about incorrect confirm password.
     */
    @FXML
    public Label cpNotice;
    /**
     * Binding eNotice label to set text.
     */
    public StringProperty eText = new SimpleStringProperty();
    /**
     * Binding codeNotice label to set text.
     */
    public StringProperty codeText = new SimpleStringProperty();
    /**
     * Binding pNotice label to set text.
     */
    public StringProperty pText = new SimpleStringProperty();
    /**
     * Binding cpNotice label to set text.
     */
    public StringProperty cpText = new SimpleStringProperty();
    /**
     * Input field object for email address
     */
    public InputField eField;
    /**
     * Input field object for verification code
     */
    public InputField vcField;
    /**
     * Input field object for new password
     */
    public InputField pField;
    /**
     * Input field object for confirm password
     */
    public InputField cpField;
    /**
     * Time before next email sending capable
     */
    public static int countdown = 60;
    /**
     * Valid time of verification code
     */
    public static int verifyTime = 300;
    /**
     * code set to select chars that make up the verification code
     */
    public char[] codeSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     * String for store verification code
     */
    public static StringBuffer code = new StringBuffer();
    /**
     * To pick up a char randomly
     */
    public Random random = new Random();
    /**
     * String for receiving the text field's input
     */
    public String emailText;
    /**
     * String to store the relative username of email address.
     */
    public String username;
    /**
     * Indicating if can resend the email
     */
    public static Boolean canSend = true;
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
     * listener for email address textfield
     */
    public ChangeListener<Boolean> focusListener1 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(email, eText, eField);
            }
        }
    };
    /**
     * listener for new password password field
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
     * listener for verification code textfield
     */
    public ChangeListener<Boolean> focusListener3 = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                connectWarning.setVisible(false);
                inputVerify(veriCode, codeText, vcField);
            }
        }
    };
    /**
     * listener for confirm password password field
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
     * @date 19/01/2021-23:40
     */
    @FXML
    public void initialize() {
        this.warning.setImage(image);
        eField = new InputField("^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", "Email address", true, false);
        vcField = new InputField("Verification code", true, false);
        pField = new InputField("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,16}$", "Password", true, false);
        cpField = new InputField("Confirm password", true, false);
        email.focusedProperty().addListener(focusListener1);
        password.focusedProperty().addListener(focusListener2);
        veriCode.focusedProperty().addListener(focusListener3);
        confirm.focusedProperty().addListener(focusListener4);
        eNotice.textProperty().bind(eText);
        codeNotice.textProperty().bind(codeText);
        pNotice.textProperty().bind(pText);
        cpNotice.textProperty().bind(cpText);
        if(!canSend) {
            sendBtn.setText("resend (" + countdown + ")");
            resetBtnText();
        }
    }

    /**
     * Send verification code by email when clicking 'Send Code' button
     * <p>  Send verification code by email when clicking 'Send Code' button
     *
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws MessagingException     MessagingException
     * @author Yuan DAI
     * @date 20/01/2021-17:33
     */
    @FXML
    public void sendCode() throws SQLException, ClassNotFoundException, MessagingException {

        emailText = email.getText();
        if (eField.getIsInvalid()) {
            return;
        }
        if (!canSend) {
            eText.set("Can't resend mail in 60 seconds!");
            if (!eField.getIsAdded()) {
                email.getStyleClass().add("textField2-error");
                eField.setIsAdded(true);
            }
            return;
        }
        if (JdbcUtil.IsConnectDatabase()) {
            connectWarning.setVisible(true);
            return;
        } else {
            connectWarning.setVisible(false);
        }
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select Email,Username from userInform where Email = '" + emailText + "'");
        if (!JdbcUtil.rs.next()) {
            eText.set("The email address has not been registered!");
            if (!eField.getIsAdded()) {
                email.getStyleClass().add("textField2-error");
                eField.setIsAdded(true);
            }
            return;
        }
        username = JdbcUtil.rs.getString("Username");
        generateCode();
        sendMail();
        canSend = false;
        sendBtn.setText("resend (" + (countdown) + ")");
        startResendCountdown();
        resetBtnText();
        startVerifyCountdown();
        JdbcUtil.closeConnect();
    }

    /**
     * Start count down for resent email
     * <p> Start count down for resent email, when count down to 0, the button is capable again.
     *
     * @author Yuan DAI
     * @date 20/01/2021-17:40
     */
    public static void startResendCountdown() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            countdown--;
                            if (countdown == 0) {
                                countdown = 60;
                                canSend = true;
                            }
                        }
                )
        );
        timeline.setCycleCount(60);
        timeline.play();
    }

    /**
     * Start count down for valid code
     * <p> When count down to 0, the string buffer storing the verification code will be cleaned
     *
     * @author Yuan DAI
     * @date 20/01/2021-17:40
     */
    public static void startVerifyCountdown() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            verifyTime--;
                            if (verifyTime == 0) {
                                verifyTime = 300;
                                code.setLength(0);
                            }
                        }
                )
        );
        timeline.setCycleCount(300);
        timeline.play();
    }

    /**
     * Update button text every seconds.
     * <p> Update button text every seconds.
     *
     * @author Yuan DAI
     * @date 21/03/2021-21:23
     */
    public void resetBtnText() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            sendBtn.setText("resend (" + countdown + ")");
                            if (canSend) {
                                sendBtn.setText("Send code");
                            }
                        }
                )
        );
        timeline.setCycleCount(countdown);
        timeline.play();
    }

    /**
     * Create password when clicking 'Create new password' button
     * <p> Update the password in database after verification
     *
     * @throws SQLException           SQLException
     * @throws IOException            IOException
     * @throws ClassNotFoundException ClassNotFoundException
     * @author Yuan DAI
     * @date 20/01/2021-16:32
     */
    @FXML
    public void createPassword() throws SQLException, IOException, ClassNotFoundException {

        if (pField.getIsInvalid() || eField.getIsInvalid() || cpField.getIsInvalid()) {
            return;
        }

        String emailVerify = email.getText();
        String codeInput = veriCode.getText();
        if (emailText == null) {
            return;
        }
        if (!emailText.equals(emailVerify)) {
            eText.set("Not allowed to input another email address after verification!");
            if (!eField.getIsAdded()) {
                email.getStyleClass().add("textField2-error");
                eField.setIsAdded(true);
            }
            return;
        }
        if (code == null) {
            return;
        }
        if (!code.toString().equals(codeInput)) {
            codeText.set("The verification code is incorrect!");
            if (!pField.getIsAdded()) {
                veriCode.getStyleClass().add("textField2-error");
                pField.setIsAdded(true);
            }
            return;
        }

        String newPassword = password.getText();
        if (JdbcUtil.IsConnectDatabase()) {
            connectWarning.setVisible(true);
            return;
        } else {
            connectWarning.setVisible(false);
        }
        sqlState = "update userInform set Password = '" + newPassword + "' where Email = '" + emailText + "'";
        pstmt = JdbcUtil.con.prepareStatement(sqlState);
        pstmt.executeUpdate();
        JdbcUtil.closeConnect();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(sendBtn.getScene().getWindow());
        alert.setTitle("Successful Reset");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! Your reset is successful.");

        alert.showAndWait();

        jumpToSignIn();
    }

    /**
     * generate verification code
     * <p>generate a 6-digit verification code consists of letters and numbers
     *
     * @author Yuan DAI
     * @date 20/01/2021-11:40
     */
    public void generateCode() {
        code.setLength(0);
        for (int i = 0; i < 6; i++) {
            code.append(codeSet[random.nextInt(36)]);
        }
    }


    /**
     * Send verification email
     * <p> Send verification email
     *
     * @throws MessagingException MessagingException
     * @author Yuan DAI
     * @date 20/01/2021-13:25
     */
    public void sendMail() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.user", "2692521364@qq.com");
        props.put("mail.password", "uyxlkgtobqbfddag");

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        Session mailSession = Session.getInstance(props, authenticator);
        MimeMessage message = new MimeMessage(mailSession);
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        InternetAddress to = new InternetAddress(emailText);
        message.setRecipient(RecipientType.TO, to);

        message.setSubject("Authentication mail from Beaver");

        Multipart multipart = new MimeMultipart("mixed");
        MimeBodyPart html = new MimeBodyPart();
        html.setContent(" <p style='text-align: center;'><img style='width:250px; height:50px' src='cid:logo'></p>" +
                "<h4>Dear " + username + ":</h4>" +
                "We have received your request of resetting password on our <b>Beaver</b> project.<br><br>" +
                "Your verification code is: <b style='color:blue'>" + code + "</b>.<br><br>" +
                "<b style='color:red'>Attention:</b> Please finish your verification in 5 minutes!<br><br>" +
                "<b><a href='http://cslinux.nottingham.edu.cn/~Team202001/'>Click here</a> to view our official website!</b>", "text/html;charset=UTF-8");
        MimeBodyPart image = new MimeBodyPart();
        URL url = this.getClass().getClassLoader().getResource("Image/MainMenuButton/logo2.png");
        image.setDataHandler(new DataHandler(new URLDataSource(url)));
//        image.setDataHandler(new DataHandler(new FileDataSource(new File("src/main/resources/Image/MainMenuButton/logo2.png"))));
        image.setContentID("logo");
        multipart.addBodyPart(html);
        multipart.addBodyPart(image);
        message.setContent(multipart);

        Transport.send(message);
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
        if (field.getInputType().equals("Verification code")) {
            if (field.getIsAdded()) {
                input.getStyleClass().remove("textField2-error");
                field.setIsAdded(false);
                noticeField.set("");
            }
            return;
        }
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
     * @date 13/01/2021-18:22
     */
    @FXML
    public void jumpToSignIn() throws IOException {
        Main.setRoot("/FXML/SignIn");
    }

}
