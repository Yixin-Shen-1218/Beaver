package Controllers;

import Main.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

/**
 * <p> The class ShowDataControllerTest is to test ShowDataController class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-16:28
 * @since 1.0
 */
public class ShowDataControllerTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of ShowDataControllerTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the SQL statement
        MainMenuController.sqlStatement = "SELECT * FROM simMEV";

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/ShowData.fxml")));

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
     * nextPage Test.
     * <p> Test the nextPage button onClickAction, whether it can go to next page.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void nextPage() {
        clickOn(Main.scene.lookup("#nextPageBt"));
    }

    /**
     * lastPage Test.
     * <p> Test the lastPage button onClickAction, whether it can go to last page.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void lastPage() {
        clickOn(Main.scene.lookup("#lastPageBt"));
    }
}