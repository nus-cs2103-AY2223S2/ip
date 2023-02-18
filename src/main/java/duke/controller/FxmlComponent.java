package duke.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public interface FxmlComponent {

    default void loadFxml(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
