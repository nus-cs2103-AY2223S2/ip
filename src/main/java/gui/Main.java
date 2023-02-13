package gui;

import java.io.IOException;
import Nerd.Nerd;
import Nerd.Ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

    private Nerd nerd = new Nerd("duke.txt");
    private Ui ui = new Ui();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNerd(nerd, ui);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().getConnectionMessage();
            fxmlLoader.<MainWindow>getController().setDefaultMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
