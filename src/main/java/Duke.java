import duke.gui.DukeGui;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Duke.
 */
public class Duke extends Application {


  private final DukeGui dukeGui = new DukeGui();

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Application.launch(DukeGui.class, args);
  }

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
      AnchorPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setDuke(dukeGui);
      stage.show();
      stage.setOnCloseRequest(event -> {
        Platform.exit();
        System.exit(0);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

