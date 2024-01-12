package Main;

import Controllers.SetPasswordController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

/**
 * <p> The Class Main is used to start the software.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 08/01/2021-16:06
 * @since 1.0
 */
public class Main extends Application {
    /**
     * Defines the stage of the software.
     */
    public static Stage primaryStage;
    /**
     * Defines the scene of the software.
     */
    public static Scene scene;
    /**
     * Defines the username
     */
    public static String username;
    /**
     * Defines the userID
     */
    public static String UID;

    /**
     * Start method of the fx project, called in main method.
     * <p> Start the game, set the first scene of the software and the name of the primaryStage.
     *
     * @param primaryStage primary stage
     * @throws Exception Runtime exception
     * @author Yixin SHEN
     * @date 08/01/2021-16:06
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("BEAVER");
        scene = new Scene(loadFXML("/FXML/SignIn"));
//        scene = new Scene(loadFXML("/FXML/MainMenu"));

        primaryStage.setScene(scene);
        Main.setlogo(primaryStage);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
                Platform.exit();
            }
        });

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * setlogo
     * <p>
     * set the logo of UI
     *
     * @param stage the primary stage
     * @author Ruibin CHEN
     * @date 2021/3/16-16:53
     */
    public static void setlogo(Stage stage) {
        stage.getIcons().add(new javafx.scene.image.Image(Main.class.getResourceAsStream("/Image/MainMenuButton/logo.png")));
    }

    /**
     * Set the scene root of the game.
     * <p> Set the scene root of the game.
     *
     * @param fxml The fxml file name.
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 08/01/2021-16:06
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    /**
     * Load the fxml file as resource.
     * <p> Load the fxml file as resource, return the load file in fx parent format.
     *
     * @param fxml The fxml file name.
     * @return javafx.scene.Parent The fxml file in parent format.
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 08/01/2021-16:06
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    /**
     * Close sign up screen and open main menu
     * <p> Close and recreate primary stage
     *
     * @throws IOException IOException
     * @author Yuan DAI
     * @date 12/01/2021-17:09
     */
    public static void toMainMenu() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        primaryStage.setTitle("Beaver");
        Main.setlogo(primaryStage);
        scene = new Scene(loadFXML("/FXML/MainMenu"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Close sign up screen and open main menu
     * <p> Close and recreate primary stage
     *
     * @throws IOException IOException
     * @author Yuan DAI
     * @date 12/01/2021-17:09
     */
    public static void toSignIn() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        primaryStage.setTitle("Beaver");
        scene = new Scene(loadFXML("/FXML/SignIn"));

        primaryStage.setScene(scene);
        Main.setlogo(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * The main method of the game.
     * <p> The main method of the game, call the launch method to start the game.
     *
     * @param args Main method args.
     * @author Yixin SHEN
     * @date 08/01/2021-16:06
     */
    public static void main(String[] args) {
        launch(args);
    }
}
