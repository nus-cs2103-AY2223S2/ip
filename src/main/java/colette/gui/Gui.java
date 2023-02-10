package colette.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for the Colette chatbot using FXML.
 */
public class Gui extends Application {

    private static HostServices hostServices;

    public static HostServices getGuiHostServices() {
        return Gui.hostServices;
    }

    @Override
    public void start(Stage stage) {
        Gui.hostServices = getHostServices();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class
                    .getClassLoader().getResource("view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Colette");
            stage.setOnCloseRequest(event ->
                HelpWindow.closeHelpWindow()
            );
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
