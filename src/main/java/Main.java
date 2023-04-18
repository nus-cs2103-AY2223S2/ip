import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for C4PO using FXML.
 */
public class Main extends Application {

    private C4PO c4po = new C4PO();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("C4PO");
            fxmlLoader.<MainWindow>getController().setC4PO(c4po);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
