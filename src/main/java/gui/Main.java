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
        stage.setResizable(false);
        stage.setTitle("Duke");
        RouterManager router = new RouterManager(stage);
        router.showLandingView();
        stage.show();
    }
}
