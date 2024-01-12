package Controllers;

import Main.Main;
import Music.ConfirmSound;
import Music.ErrorSound;
import ToolPackage.JdbcUtil;
import TreeControl.TreeItemObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * <p> The Class FilterController is the controller class of Filter.fxml.
 * Initialize Filter scene that use to import the data sets.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 01/02/2021-13:03
 * @since 1.0
 */
public class FilterController {
    /**
     * ComboBox area that shows the statistic of scenario
     */
    public ComboBox TableBox;
    /**
     * Button that used to add conditions
     */
    public Button AddConditionBt;
    /**
     * Button that used to confirm the conditions
     */
    public Button ConfirmBt;
    /**
     * VBox area that used to show the conditions
     */
    public VBox ConditionBox;
    /**
     * Label that show the sql statement
     */
    public Label previewLable;
    /**
     * Button that used to preview the sql statement
     */
    public Button PreviewBt;
    /**
     * TextField that show the name of data set
     */
    public TextField DataSetName;
    /**
     * The UI stage of primaryStage
     */
    private Stage primaryStage = Main.primaryStage;


    /**
     * Initialize the filter scene.
     * <p> Build the table box in the scene.
     *
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @author Yixin SHEN
     * @date 02/02/2021-15:24
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        buildTableBox();
    }


    /**
     * Build the VBOX -- Condition Box in the scene.
     * <p> Load the content in the VBox.
     *
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @author Yixin SHEN
     * @date 02/02/2021-15:26
     */
    public void buildTableBox() throws SQLException, ClassNotFoundException {
        // Connect to database
        JdbcUtil.connectDatabase();
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("select * from TableInfo where UID = " + Main.UID);

        if (!JdbcUtil.rs.next()) {
            return;
        } else {
            TableBox.getItems().add(JdbcUtil.rs.getString("TableName").replace(Main.username + "_", ""));
            while (JdbcUtil.rs.next()) {
                TableBox.getItems().add(JdbcUtil.rs.getString("TableName").replace(Main.username + "_", ""));
            }
        }

        // Close connect
        JdbcUtil.closeConnect();


        TableBox.getSelectionModel().selectFirst();

        // Add a listener, if user change the selected table, clear the vbox content
        TableBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                ConditionBox.getChildren().clear();
            }
        });
    }


    /**
     * Add condition to the Condition Box.
     * <p> When user click the addCondition button, add a HBox to the Condition box, which allows user to set the filter condition.
     *
     * @param actionEvent Mouse Click Event
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @author Yixin SHEN
     * @date 02/02/2021-15:28
     */
    public void AddCondition(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (TableBox.getValue() == null) {
            newWarning1();
            return;
        }

        HBox hBox = new HBox();
        ComboBox<String> ColumnBox = new ComboBox<>();
        ColumnBox.setPrefWidth(100);

        Insets insets = new Insets(3);
        HBox.setMargin(ColumnBox, insets);


        ArrayList<String> Operators = new ArrayList<>(Arrays.asList("=", "<>", ">", "<", ">=", "<="));
        ArrayList<String> LogicList = new ArrayList<>(Arrays.asList("", "AND", "OR"));

        ComboBox<String> OperatorBox = new ComboBox<>(FXCollections.observableList(Operators));
        HBox.setMargin(OperatorBox, insets);

        TextField textField = new TextField();
        HBox.setMargin(textField, insets);

        ImageView TrashBin = new ImageView(new Image(getClass().getResourceAsStream("/Image/MainMenuButton/TrashBin.png")));
        Button button = new Button("", TrashBin);
        HBox.setMargin(button, insets);

        ComboBox<String> LogicBox = new ComboBox<>(FXCollections.observableList(LogicList));
        HBox.setMargin(LogicBox, insets);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        // Connect to database
        JdbcUtil.connectDatabase();
        JdbcUtil.rs = JdbcUtil.stmt.executeQuery("SHOW COLUMNS FROM " + Main.username + "_" + TableBox.getValue());

        while (JdbcUtil.rs.next()) {
            ColumnBox.getItems().add(JdbcUtil.rs.getString("Field"));
        }

        // Close connect
        JdbcUtil.closeConnect();

        ColumnBox.getSelectionModel().selectFirst();
        OperatorBox.getSelectionModel().selectFirst();
        LogicBox.getSelectionModel().selectFirst();

        hBox.getChildren().addAll(ColumnBox, OperatorBox, textField, LogicBox, region, button);

        ConditionBox.getChildren().add(hBox);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConditionBox.getChildren().remove(hBox);
            }
        });
    }

    /**
     * Confirm the conditions what have set.
     * <p> If the input is valid, prompt a CONFIRMATION dialog; else, prompt a warning dialog.
     *
     * @param actionEvent Mouse Click Event
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 02/02/2021-15:32
     */
    public void confirm(ActionEvent actionEvent) throws IOException {
        if (ConditionBox.getChildren().size() == 0) {
            newWarning2();
            return;
        }

        String sqlStatement = "SELECT * FROM " + Main.username + "_" + TableBox.getValue() + " WHERE ";

        for (int i = 0; i < ConditionBox.getChildren().size(); i++) {
            HBox hBox = (HBox) ConditionBox.getChildren().get(i);
            ComboBox ColumnBox = (ComboBox) hBox.getChildren().get(0);
            ComboBox OperatorBox = (ComboBox) hBox.getChildren().get(1);
            TextField textField = (TextField) hBox.getChildren().get(2);
            ComboBox LogicBox = (ComboBox) hBox.getChildren().get(3);

            if (isDouble(textField.getText())) {
                sqlStatement = sqlStatement.concat(ColumnBox.getValue() + " " + OperatorBox.getValue() + " " + textField.getText() + " " + LogicBox.getValue() + " ");
            } else {
                sqlStatement = sqlStatement.concat(ColumnBox.getValue() + " " + OperatorBox.getValue() + " '" + textField.getText() + "' " + LogicBox.getValue() + " ");
            }
        }

        System.out.println(sqlStatement);

        if (isInputValid()) {
            ConfirmSound.getInstance().playMusic();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(TableBox.getScene().getWindow());
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Add a new data set");
            alert.setContentText("Are you sure you want to do this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    TreeItemObject dataSet = new TreeItemObject(2, false, DataSetName.getText(), sqlStatement);

                                MainMenuController.treeItemCollection.getDataSetsList().add(dataSet);

                    Stage filterStage = (Stage) ConditionBox.getScene().getWindow();
                    filterStage.close();

                    primaryStage.setScene(new Scene(Main.loadFXML("/FXML/MainMenu")));
                } else {
                    System.out.println("cancel");
                }
        } else {
            newWarning2();
        }
    }

    /**
     * To check whether the inputs are valid or not.
     * <p> If all the inputs are valid, return true; else, return false.
     *
     * @return boolean
     * @author Yixin SHEN
     * @date 02/02/2021-15:41
     */
    public boolean isInputValid() {
        int size = ConditionBox.getChildren().size();

        // Every textField should not be empty
        for (int i = 0; i < size; i++) {
            HBox hBox = (HBox) ConditionBox.getChildren().get(i);
            TextField textField = (TextField) hBox.getChildren().get(2);

            if (textField.getText().equals("")) {
                return false;
            }
        }

        // Except the last LogicBox, every logic box should not be empty
        for (int i = 0; i < size - 1; i++) {
            HBox hBox = (HBox) ConditionBox.getChildren().get(i);
            ComboBox LogicBox = (ComboBox) hBox.getChildren().get(3);

            if (LogicBox.getValue() == "") {
                return false;
            }
        }

        // The last LogicBox should be empty
        HBox hBox = (HBox) ConditionBox.getChildren().get(size - 1);
        ComboBox LogicBox = (ComboBox) hBox.getChildren().get(3);
        if (LogicBox.getValue() != "") {
            return false;
        }

        // The data set name textField should not be empty
        if (DataSetName.getText().equals("")) {
            return false;
        }

        return true;
    }


    /**
     * Preview the sql query according to the contents of condition box.
     * <p> Click the button, allowing user to preview the sql query in the label.
     *
     * @param actionEvent Mouse Click Event
     * @author Yixin SHEN
     * @date 02/02/2021-15:41
     */
    public void preview(ActionEvent actionEvent) {
        String sqlStatement = "SELECT * FROM " + TableBox.getValue() + " WHERE ";

        for (int i = 0; i < ConditionBox.getChildren().size(); i++) {
            HBox hBox = (HBox) ConditionBox.getChildren().get(i);
            ComboBox ColumnBox = (ComboBox) hBox.getChildren().get(0);
            ComboBox OperatorBox = (ComboBox) hBox.getChildren().get(1);
            TextField textField = (TextField) hBox.getChildren().get(2);
            ComboBox LogicBox = (ComboBox) hBox.getChildren().get(3);

            if (isDouble(textField.getText())) {
                sqlStatement = sqlStatement.concat(ColumnBox.getValue() + " " + OperatorBox.getValue() + " " + textField.getText() + " " + LogicBox.getValue() + " ");
            } else {
                sqlStatement = sqlStatement.concat(ColumnBox.getValue() + " " + OperatorBox.getValue() + " '" + textField.getText() + "' " + LogicBox.getValue() + " ");
            }
        }

        previewLable.setText(sqlStatement);
    }

    /**
     * Check whether the string is double or not.
     * <p> If it is double, return true; else, return false.s
     *
     * @param str The textFiled input
     * @return boolean
     * @author Yixin SHEN
     * @date 02/02/2021-15:43
     */
    public boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * Generate empty table warning dialog.
     * <p> if he/she have not import any table, pop up a waning message.
     *
     * @author Yixin SHEN
     * @date 02/02/2021-15:43
     */
    public void newWarning1() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(TableBox.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Please import valid table first!");

        alert.showAndWait();
    }

    /**
     * Generate invalid sql statement warning dialog.
     * <p> if the input content is invalid, pop up a waning message.
     *
     * @author Yixin SHEN
     * @date 02/02/2021-15:43
     */
    public void newWarning2() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(TableBox.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Name is NULL or SQL query is invalid, please check!");

        alert.showAndWait();
    }
}
