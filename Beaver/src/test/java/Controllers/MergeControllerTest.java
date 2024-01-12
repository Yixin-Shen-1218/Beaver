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

import static org.junit.Assert.*;

/**
 * <p> The class MergeControllerTest is to test MergeController class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 16/03/2021-19:44
 * @since 1.0
 */
public class MergeControllerTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of MergeControllerTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 16/03/2021-19:44
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the username and UID
        Main.username = "Test";
        Main.UID = "17";
        MainMenuController.treeItemCollection = new TreeItemCollection();

        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/Merge.fxml")));

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
     * Test addComparison method.
     * <p> Click the AddBtn and check whether the comparison is added
     *
     * @author Yixin SHEN
     * @date   16/03/2021-19:44
     */
    @Test
    public void addComparison() {
        clickOn(Main.scene.lookup("#AddBtn"));
    }

    /**
     * Test showSymbols method.
     * <p>  Click the show symbol check box to check whether the symbols is displayed.
     *
     * @author Yixin SHEN
     * @date   16/03/2021-19:45
     */
    @Test
    public void showSymbols() {
        clickOn(Main.scene.lookup("#ShowSymbolCheck"));
    }
}