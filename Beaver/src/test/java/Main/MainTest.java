package Main;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import junit.framework.TestCase;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

/**
 * <p> The class MainTest is to test Main class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-12:23
 * @since 1.0
 */
public class MainTest extends ApplicationTest {
    /**
     * Start method of the fx project, called in main method.
     * <p> Start the software, set the first scene of the software and the name of the stage.
     *
     * @param primaryStage The stage of MainTest.
     * @throws Exception Exception
     * @author Yixin SHEN
     * @date 07/03/2021-12:23
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        Main.scene = new Scene(FXMLLoader.load(Main.class.getResource("/FXML/SignIn.fxml")));

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
     * SetRoot Test.
     * <p> Test the SetRoot method. Call the SetRoot method to check whether the Main scene will be changed.
     *
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 07/03/2021-12:23
     */
    @Test
    public void testSetRoot() throws IOException {
        Main.setRoot("/FXML/SetPassword");
    }

    /**
     * loadFXML Test.
     * <p> Test the loadFXML method.
     * Get the return value in Parent type, and set the Main scene as parent node to check whether it will be changed to sign un page.
     *
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 07/03/2021-12:23
     */
    @Test
    public void testloadFXML() throws IOException {
        Parent parent = Main.loadFXML("/FXML/SignUp");
        Main.scene.setRoot(parent);
    }
}