package Controllers;

import Main.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 01/03/2021-22:43
 * @since 1.0
 */
public class UserGuideController {
    /**
     * Previous button.
     */
    @FXML
    public Button prev;
    /**
     * Next button.
     */
    @FXML
    public Button next;
    /**
     * Label of notice messages.
     */
    @FXML
    public Label note;
    /**
     * Label of current page.
     */
    @FXML
    public Label current;
    /**
     * Label of total page.
     */
    @FXML
    public Label total;
    /**
     * ImageView to contain screenshots.
     */
    @FXML
    public ImageView imgPlace;
    /**
     * Binding note label to set text.
     */
    public StringProperty noteText = new SimpleStringProperty();
    /**
     * Binding current label to set text.
     */
    public StringProperty currentText = new SimpleStringProperty();
    /**
     * Binding total label to set text.
     */
    public StringProperty totalText = new SimpleStringProperty();
    /**
     * List to contain guides.
     */
    List<String> guides = new ArrayList<String>();
    /**
     * Current guide type.
     */
    String currentGuide;
    /**
     * Total number of pages.
     */
    int totalNum;
    /**
     * Current number of pages.
     */
    int currentNum;

    /**
     * Initialize the UserGuideController.
     * <p> Initialize the UserGuideController.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:23
     */
    @FXML
    public void initialize() throws IOException {
        prev.setShape(new Circle(10));
        next.setShape(new Circle(10));
        total.textProperty().bind(totalText);
        current.textProperty().bind(currentText);
        note.textProperty().bind(noteText);
        importData();
    }

    /**
     * Show import data guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void importData() throws IOException {
        totalNum = 9;
        currentNum = 1;
        guides.clear();
        guides.add("To import model: File >> Import >> Import Model or click the button on the toolbar. The model imported will be saved in local.");
        guides.add("To upload data sets to database: Click the button on the toolbar.");
        guides.add("Click Select a File to find the file or drag the file to the box. Rename the data sets and click confirm.");
        guides.add("Click import button to upload the file to database, and click cancel button to cancel the import operation.");
        guides.add("To import scenario: File >> Import >> Import Scenario or click the button on the toolbar.");
        guides.add("To import data sets: File >> Import >> Import Data Sets or click the button on the toolbar.");
        guides.add("Fill all the attributes correctly and then click confirm.");
        guides.add("If an incorrect sql statement format selected, a warning dialog will be popped out, the import operation can’t continue.");
        guides.add("Else the filtrated data sets will be imported to local and can be seen in “My Data Sets” in the tree item on the left side.");
        currentGuide = "ImportData";
        showGuide();
    }

    /**
     * Show model application guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void modelApp() throws IOException {
        totalNum = 4;
        currentNum = 1;
        guides.clear();
        guides.add("Choose the model, data sets and scenario, then click model application button. It may take a few minutes to output the result.");
        guides.add("Charts generated below. A precise value will be popped out if putting mouse on the result point.");
        guides.add("To save the results to history record, click the save button. Enter the new result’s name, then click yes.");
        guides.add("The record can be seen in the content tree.");
        currentGuide = "ModelApp";
        showGuide();
    }

    /**
     * Show export data guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void exportData() throws IOException {
        totalNum = 3;
        currentNum = 1;
        guides.clear();
        guides.add("Export DR Chart as PNG: File >> Export >> Export DR Chart as PNG or clicking the button.");
        guides.add("Export Loglike Chart as PNG: File >> Export >> Export Loglike Chart as PNG or clicking the button.");
        guides.add("Export Chart Data as TXT: File >> Export >> Export Chart Data as TXT or clicking the button.");
        currentGuide = "ExportData";
        showGuide();
    }

    /**
     * Show content tree guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void contentTree() throws IOException {
        totalNum = 7;
        currentNum = 1;
        guides.clear();
        guides.add("Open any files by right clicking the file and click “Open” or by double clicking the file.");
        guides.add("If open data sets, click last page, next page button or enter the page number in the input field to view other pages.");
        guides.add("If open history record, the correspond graph will be displayed.");
        guides.add("Rename File in Content Tree: Right click >> Rename.");
        guides.add("Delete File in Content Tree: Right click >> Delete.");
        guides.add("Favour File in Content Tree: Right click >> Favour to favour a file.");
        guides.add("And replicate Right click >> Favour to backout favour.");
        currentGuide = "ContentTree";
        showGuide();
    }

    /**
     * Show open recent file guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void openRecent() throws IOException {
        totalNum = 1;
        currentNum = 1;
        guides.clear();
        guides.add("To view recent history records, click File >> Open Recent.");
        currentGuide = "OpenRecent";
        showGuide();
    }


    /**
     * Show merge guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void merge() throws IOException {
        totalNum = 5;
        currentNum = 1;
        guides.clear();
        guides.add("Merge function is aimed to compare history records, generally used to compare charts produced from same data sets, but different scenarios.");
        guides.add("Click File >> Merge.");
        guides.add("Add history charts and set the series name.");
        guides.add("Click delete button to delete the attribute, click merge to merge the graphs.");
        guides.add("If Series Names are not all set correctly, a warning dialog will be popped out.");
        currentGuide = "Merge";
        showGuide();
    }

    /**
     * Show sign out guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void signOut() throws IOException {
        totalNum = 1;
        currentNum = 1;
        guides.clear();
        guides.add("File>>Sign Out, then back to the Sign In page.");
        currentGuide = "SignOut";
        showGuide();
    }

    /**
     * Show tree file guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void quit() throws IOException {
        totalNum = 1;
        currentNum = 1;
        guides.clear();
        guides.add("To exit Beaver, click File >> Quit.");
        currentGuide = "Quit";
        showGuide();
    }

    /**
     * Show user inform guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void userInform() throws IOException {
        totalNum = 3;
        currentNum = 1;
        guides.clear();
        guides.add("Click the profile in the top-right corner.");
        guides.add("In user inform page, you username, email and your tables on database are shown, click the delete button to delete tables.");
        guides.add("If you clicked the delete button, a confirm dialog will be popped out, click yes to continue.");
        currentGuide = "UserInform";
        showGuide();
    }

    /**
     * Show view guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void view() throws IOException {
        totalNum = 3;
        currentNum = 1;
        guides.clear();
        guides.add("View >> Show ToolBar.");
        guides.add("View >> Show Content as Tree.");
        guides.add("View >> Show Chart Symbols.");
        currentGuide = "View";
        showGuide();
    }

    /**
     * Show help guide when clicking the button.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:44
     */
    @FXML
    public void help() throws IOException {
        totalNum = 3;
        currentNum = 1;
        guides.clear();
        guides.add("Help >> User Guide.");
        guides.add("Help >> About Beaver");
        guides.add("File >> About us.\n" +
                "Click it to turn to our official website.");
        currentGuide = "Help";
        showGuide();
    }

    /**
     * Show the relative guide.
     *
     * @throws IOException IOException.
     * @author Yuan DAI
     * @date 02/03/2021-15:56
     */
    public void showGuide() throws IOException {
        showContent(currentNum);
    }


    /**
     * Show the messages and images.
     *
     * @param num Number of relative image and guide.
     * @author Yuan DAI
     * @date 02/03/2021-16:07
     */
    public void showContent(int num) {
        Image image = new Image("/Image/UserGuide/" + currentGuide + "/"+ currentGuide + num + ".png");
        this.imgPlace.setImage(image);
        noteText.set(guides.get(num - 1));
        currentText.set(currentNum + "");
        totalText.set("/" + totalNum);
    }

    /**
     * Move to previous page when clicking the 'prev' button.
     *
     * @author Yuan DAI
     * @date 02/03/2021-16:13
     */
    @FXML
    public void prevPage() {
        if (currentNum == 1)
            return;
        showContent(--currentNum);
    }

    /**
     * Move to next page when clicking the 'next' button.
     *
     * @author Yuan DAI
     * @date 02/03/2021-16:14
     */
    @FXML
    public void nextPage() {
        if (currentNum == totalNum)
            return;
        showContent(++currentNum);
    }

}
