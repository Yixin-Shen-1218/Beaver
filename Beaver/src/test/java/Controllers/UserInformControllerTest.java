package Controllers;


import Main.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * <p> The class UserInformControllerTest is to test UserInformController class.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 09/03/2021-11:13
 * @since 1.0
 */
public class UserInformControllerTest extends ApplicationTest {
    /**
     * FXML loader to load SignUp page.
     */
    FXMLLoader loader = new FXMLLoader();
    /**
     * SignUpController instance.
     */
    UserInformController userInformController;

    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of UserInformControllerTest.
     * @throws Exception Exception
     * @author Yuan DAI
     * @date 07/03/2021-13:17
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.username = "Test";
        Main.UID = "17";

        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        loader.setLocation(getClass().getResource("/FXML/UserInform.fxml"));
        Main.scene = new Scene(loader.load());
        userInformController =(UserInformController) loader.getController();
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
     * Test if delete button work.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:42
     */
    @Test
    public void testAddTable() {
        clickOn(Main.scene.lookup("#1"));
    }

}
