package gui;

import gui.managers.RouterManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {
    public Main() {}

    @Override
    public void start(Stage stage) {
        // Default stage settings
        stage.setResizable(false);
        stage.setTitle("Duke");
        stage.show();

        // Initializes the router object
        RouterManager router = RouterManager.createRouterSingleton(stage);
        router.showLandingView();
    }
}
