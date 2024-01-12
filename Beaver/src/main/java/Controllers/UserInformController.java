package Controllers;


import Main.Main;
import Music.ConfirmSound;
import ToolPackage.JdbcUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.geometry.Insets;

import java.awt.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


/**
 * <p> The Class UserInformController is the controller class of UserInform.fxml.
 * Initialize UserInform scene.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 27/02/2021-16:55
 * @since 1.0
 */
public class UserInformController {
    /**
     * Show user name.
     */
    @FXML
    public Text name;
    /**
     * Show email address.
     */
    @FXML
    public Text email;
    /**
     * Vbox to contain elements.
     */
    @FXML
    public VBox vbox;
    /**
     * Binding name label to set text.
     */
    public StringProperty nameText = new SimpleStringProperty();
    /**
     * Binding email label to set text.
     */
    public StringProperty emailText = new SimpleStringProperty();

    /**
     * Initialize the UserInformController.
     * <p> Initialize the UserInformController.
     *
     * @throws SQLException           SQLException.
     * @throws ClassNotFoundException ClassNotFoundException.
     * @author Yuan DAI
     * @date 28/02/2021-16:57
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        name.textProperty().bind(nameText);
        email.textProperty().bind(emailText);
        setInform();
        addTable();
    }

    /**
     * Set user inform.
     * <p> Get user information from database
     *
     * @throws SQLException           SQLException.
     * @throws ClassNotFoundException ClassNotFoundException.
     * @author Yuan DAI
     * @date 28/02/2021-17:21
     */
    public void setInform() throws SQLException, ClassNotFoundException {
        JdbcUtil.connectDatabase();
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select Username, Email from userInform where Username = '" + Main.username + "'");
        JdbcUtil.rs.next();
        nameText.set(Main.username);
        emailText.set("Email: " + (JdbcUtil.rs.getString("Email")));
        JdbcUtil.closeConnect();
    }

    /**
     * Add table records.
     * <p> Add table records and set delete buttons.
     *
     * @throws SQLException           SQLException.
     * @throws ClassNotFoundException ClassNotFoundException.
     * @author Yuan DAI
     * @date 02/03/2021-11:12
     */
    public void addTable() throws SQLException, ClassNotFoundException {
        String prefix = Main.username + "_";
        Integer id = 1;
        JdbcUtil.connectDatabase();
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select * from TableInfo where UID = '" + Main.UID + "'");
        while (JdbcUtil.rs.next()) {
            if (JdbcUtil.rs.getString("UID").equals(Main.UID)) {
                Insets insets = new Insets(3);
                HBox hbox = new HBox();
                Label tableName = new Label();
                tableName.setLayoutX(60);
                tableName.setStyle("border:0;background-color:transparent;color: #666464;height: auto;");

                HBox.setMargin(tableName, insets);
                String TableName = JdbcUtil.rs.getString("TableName").replaceAll("^" + prefix + "+?", "");
                tableName.setText(TableName);

                ImageView TrashBin = new ImageView(new Image(getClass().getResourceAsStream("/Image/MainMenuButton/TrashBin.png")));
                Button button = new Button("", TrashBin);
                HBox.setMargin(button, insets);

                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);

                hbox.getChildren().addAll(tableName, region, button);
                vbox.getChildren().add(hbox);
                button.setId(id.toString());
                id++;
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            ConfirmSound.getInstance().playMusic();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.initOwner(vbox.getScene().getWindow());
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Delete Table");
                            alert.setContentText("Are you sure you want to do this?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                String sqlState;
                                PreparedStatement pstmt;
                                JdbcUtil.connectDatabase();
                                JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select TableID from TableInfo where UID = '" + Main.UID + "' and TableName = '" + prefix + tableName.getText() + "'");
                                JdbcUtil.rs.next();
                                String tID = JdbcUtil.rs.getString("TableID");
                                sqlState = "delete from TableInfo where TableID = ?";
                                pstmt = JdbcUtil.con.prepareStatement(sqlState);
                                pstmt.setString(1, tID);
                                pstmt.executeUpdate();
                                sqlState = "drop table " + prefix + tableName.getText() + "";
                                pstmt = JdbcUtil.con.prepareStatement(sqlState);
                                pstmt.executeUpdate();
                                JdbcUtil.closeConnect();
                                vbox.getChildren().remove(hbox);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
        JdbcUtil.closeConnect();
    }
}
