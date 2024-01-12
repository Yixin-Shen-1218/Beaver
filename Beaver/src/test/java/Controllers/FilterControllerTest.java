package Controllers;

import Main.Main;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import junit.framework.TestCase;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * <p> The class FilterTest is to test FilterController class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-12:54
 * @since 1.0
 */
public class FilterControllerTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of FilterControllerTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 07/03/2021-12:54
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the username and UID
        Main.username = "Test";
        Main.UID = "17";

        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/Filter.fxml")));

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
     * AddCondition Test.
     * <p> Test the AddCondition method. Click the AddConditionBt to check whether the condition is added successfully.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-12:54
     */
    @Test
    public void testAddCondition() {
        clickOn(Main.scene.lookup("#AddConditionBt"));
    }

    /**
     * Confirm Test.
     * <p> Test the Confirm method. Click the ConfirmBt to check if the sql statement is invalid, there is a warning dialog.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-12:54
     */
//    @Test
//    public void testConfirm() {
//        clickOn(Main.scene.lookup("#ConfirmBt"));
//    }

    /**
     * Preview Test.
     * <p> Test the Preview method. Click the PreviewBt to show the sqlStatement which has been set.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-12:54
     */
    @Test
    public void testPreview() {
        clickOn(Main.scene.lookup("#PreviewBt"));
    }

    /**
     * IsDouble Test.
     * <p> Test whether the IsDouble method can return a bool value that show the input string is double or not.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-12:54
     */
    @Test
    public void testIsDouble() {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        assertTrue(pattern.matcher("11.22").matches());
        assertFalse(pattern.matcher("a").matches());
    }
}