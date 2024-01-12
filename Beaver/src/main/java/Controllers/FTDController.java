package Controllers;

import DataProcessing.DataOperator;
import Music.ErrorSound;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Optional;

/**
 * <p>
 * The FileToDatabase stage controller
 *
 * @author Ruibin CHEN
 * @version 1.0
 * @date 2021/2/11-0:48
 * @since 1.0
 */
public class FTDController {
    /**
     * Button that used to select the file once clicked
     */
    public Button choose;
    /**
     * Button that used to confirm and change the file name
     */
    public Button confirm;
    /**
     * Button that used to start importing data from file to database
     */
    public Button importButton;
    /**
     * Button that used to cancel importing data from file to database
     */
    public Button cancelButton;
    /**
     * ImageView that used to display the download symbol
     */
    public ImageView fileImg;
    /**
     * Text that used to display the name of selected file
     */
    public Text fileName;
    /**
     * The object of data processor to deal with the data
     */
    private DataOperator dataOperator;
    /**
     * The string that used to store the path of file
     */
    private String path;
    /**
     * Text that used to display the path of file
     */
    @FXML
    private Text filePath;
    /**
     * TextField that used to allow users to input the name of file
     */
    @FXML
    private TextField inputName;
    /**
     * ProgressBar that used to show the current progress
     */
    @FXML
    private ProgressBar progressBar;
    /**
     * Label that used to show the status of task
     */
    @FXML
    private Label percent;
    /**
     * AnchorPane that used to contain different controls
     */
    @FXML
    private AnchorPane fileBox;
    /**
     * Text that used to guide users of dragging operation
     */
    @FXML
    private Text dragText;
    /**
     * Thread that used to run the data importing code
     */
    private Thread task1;

    /**
     * initialize
     * <p>
     * The initialization function of controller.
     * Used to initialize some fields.
     * @author Ruibin CHEN
     * @date   2021/3/13-17:02
     */
    public void initialize() {
        dataOperator = new DataOperator();
        filePath.setText(null);

        inputName.setPromptText("Enter the prefer data name");
        dragFile();
        setFileImgLister();


        MainMenuController.ImportDataStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                if (percent.getText() != "success" && percent.getText() != "canceled" && percent.getText() != ""){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initOwner(fileBox.getScene().getWindow());
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("You still have task running ");
                    alert.setContentText("Are you sure you want to stop it?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {
                            System.out.println("close");
                            dataOperator.cancel();
                        } else {
                            System.out.println("cancel");
                            event.consume();
                        }
                }


            }
        });
    }

    /**
     * chooseOnClick
     * <p>
     * Button event function that once the select file button is clicked,
     * call getFilePath function to get the path.
     *
     * @param actionEvent button click event
     * @author Ruibin CHEN
     * @date   2021/3/13-17:03
     */
    public void chooseOnClick(ActionEvent actionEvent) {
        path = getFilePath();
        if (path != null) {

            dataOperator.readCsvFile(path);

            filePath.setText(path);
            fileName.setText(dataOperator.getFileName());
        }
    }

    /**
     * confirmOnClick
     * <p>
     * Button event function that once the confirm button is clicked,
     * update the filename filed.
     * If the file name is invalid, alert the warning to user
     *
     * @param actionEvent button click event
     * @author Ruibin CHEN
     * @date   2021/3/13-17:11
     */
    public void confirmOnClick(ActionEvent actionEvent) {
        String inputText = inputName.getText();
        if (!inputText.equals("") && isLetterDigit(inputText)) {
            dataOperator.setFileName(inputText);
            System.out.println("Set Name: " + inputText);
            fileName.setText(dataOperator.getFileName());
        } else {
            FileNameWarning();
        }
    }

    /**
     * Check the FileName in the confirm box is valid or not
     * <p> Check the FileName in the confirm box is valid or not, return a bool value.
     *
     * @param str The new file name
     * @return boolean whether the string is legal
     * @author Yixin SHEN
     * @date 09/03/2021-13:56
     */
    public boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    /**
     * importButtonOnClick
     * <p>
     * Button event function that once the import button is clicked,
     * create new thread to execute the sql statement to import data to database
     *
     * @param actionEvent button click event
     * @author Ruibin CHEN
     * @date   2021/3/13-17:15
     */
    public void importButtonOnClick(ActionEvent actionEvent) {
        System.out.println("Import");
        if (path != null) {
            System.out.println("Import2");

            task1 = new Thread(dataOperator);
            task1.start();
            progressBar.progressProperty().bind(dataOperator.progressProperty());
            percent.textProperty().bind(dataOperator.messageProperty());
        }
    }

    /**
     * cancelButtonOnClick
     * <p>
     * Button click event that once the cancel button is clicked,
     * stop the execution of the task
     * @param actionEvent button click event
     * @author Ruibin CHEN
     * @date   2021/3/13-17:18
     */
    public void cancelButtonOnClick(ActionEvent actionEvent) {
        dataOperator.cancel();
    }

    /**
     * getFilePath
     * <p>
     * Use fileChooser to allow user to choose their csv file on computer.
     * The initial path is the desktop.
     * Limitation is set when choosing the file, only the file in csv form can be seen.
     *
     * @return java.lang.String the path of selected csv file location on computer
     * @author Ruibin CHEN
     * @date 2021/1/24-22:48
     */
    public String getFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your " + " data file.");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/desktop"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(" data file", "*.csv"));
        File result = fileChooser.showOpenDialog(MainMenuController.ImportDataStage);
        if (result != null) {
            System.out.println(result.getAbsolutePath());
            String tempName = result.getName();
            dataOperator.setFileName(tempName.substring(0, tempName.lastIndexOf(".")));
            return result.getPath();
        }
        return null;
    }

    /**
     * dragFile
     * <p>
     * Controller function that handle the event that user drag a file into the field.
     * And add limitations of input file.
     *
     * @author Ruibin CHEN
     * @date   2021/3/13-17:19
     */
    public void dragFile() {
        fileBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != fileBox
                        && event.getDragboard().hasFiles() && event.getDragboard().getFiles().toString().contains(".csv")) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        fileBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles() && db.getFiles().toString().contains(".csv")) {
                    String f = db.getFiles().toString().replace("[", "");
                    f = f.replace("]", "");
                    path = f;
                    dataOperator.readCsvFile(f);
                    filePath.setText(f);

                    String[] pathSplits = f.split("\\\\");
                    String tempName = pathSplits[pathSplits.length - 1];
                    dataOperator.setFileName(tempName.substring(0, tempName.lastIndexOf(".")));

                    fileName.setText(dataOperator.getFileName());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

    }

    /**
     * setFileImgLister
     * <p>
     * Create a list to store the input data set file and display it on UI
     *
     * @author Ruibin CHEN
     * @date   2021/3/13-17:22
     */
    public void setFileImgLister() {
        filePath.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                dragText.setVisible(false);
                Image image = new Image(getClass().getResourceAsStream("/Image/MainMenuButton/file.png"));
                fileImg.setImage(image);
            }
        });
    }

    /**
     * Generate invalid fileName warning dialog.
     * <p> If the set name in the confirm box is invalid, prompt the warning dialog.
     *
     * @author Yixin SHEN
     * @date 09/03/2021-13:57
     */
    public void FileNameWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(choose.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Please only enter number and letter!");

        alert.showAndWait();
    }
}
