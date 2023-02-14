package wtd.javafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import wtd.Wtd;

public class Main extends Application{
    private Wtd wtd = new Wtd("data/wtd.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setWtd(wtd);
            controller.showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
