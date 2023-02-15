package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    public Stage stage;
    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setTitle("TrumpBot");

        Image stageIcon = new Image(this.getClass().getResourceAsStream("/images/trump_icon.jpg"));
        stage.getIcons().add(stageIcon);

        showLandingView();
    }

    /**
     * Sets the scene to the landing view.
     */
    public void showLandingView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Landing.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<duke.ui.Landing>getController().goToMainPage(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the scene to the Main Page.
     */
    public void showMainPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<duke.ui.MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
