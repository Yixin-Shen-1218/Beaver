package Controllers;

import DataProcessing.DataOperator;
import Main.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

/**
 * <p> The class FTDControllerTest is to test FTDController class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-15:11
 * @since 1.0
 */
public class FTDControllerTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of FTDControllerTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 07/03/2021-15:11
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenuController.ImportDataStage = primaryStage;
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/FileToDatabase.fxml")));

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
     * ChooseOnClick Test.
     * <p> Test the choose button onClickAction, whether it can open a fileChooser.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-15:11
     */
    @Test
    public void testChooseOnClick() {
        clickOn(Main.scene.lookup("#choose"));
    }

    /**
     * ConfirmOnClick Test.
     * <p> Test the confirm button onClickAction, whether it can change the text content.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-15:11
     */
    @Test
    public void testConfirmOnClick() {
        TextField textField = (TextField) Main.scene.lookup("#inputName");
        textField.setText("TestFile");
        clickOn(Main.scene.lookup("#confirm"));

        Text text = (Text) Main.scene.lookup("#fileName");
        assertEquals("TestFile", text.getText());
    }

    /**
     * ImportButtonOnClick Test.
     * <p> Test the import button onClickAction, whether it can upload the file to database.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-15:11
     */
    @Test
    public void testImportButtonOnClick() {
        clickOn(Main.scene.lookup("#importButton"));
    }
}