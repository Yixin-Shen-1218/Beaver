package Controllers;

import Main.Main;
import TreeControl.TreeItemCollection;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * <p> The class MainMenuControllerTest is to test MainMenuController class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-16:28
 * @since 1.0
 */
public class MainMenuControllerTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of MainMenuControllerTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the username and UID
        Main.username = "Test";
        Main.UID = "17";
        MainMenuController.treeItemCollection = new TreeItemCollection();

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/MainMenu.fxml")));

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
     * RunModel Test.
     * <p> Test the run button onClickAction, whether it can run model test.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void testRunModel() {
        clickOn(Main.scene.lookup("#runBtn"));
    }
}