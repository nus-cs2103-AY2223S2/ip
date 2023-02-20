package voile.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * Common interface for all GUI components that can be loaded with FXML.
 */
public interface FxmlComponent {

    /**
     * Loads this component from a FXML file.
     *
     * @param fxmlPath path to the FXML file
     */
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
