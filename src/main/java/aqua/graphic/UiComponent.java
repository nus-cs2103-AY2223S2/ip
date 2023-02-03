package aqua.graphic;

import java.io.IOException;

import aqua.util.FileUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


/**
 * A custom JavaFx node created from an FXML file.
 *
 * <p>Structure adapted from
 * https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/ui/UiPart.java
 *
 * @param <T> the type of the root node.
 */
public abstract class UiComponent<T extends Node> {
    /** FXML directory path relative to the resource folder. */
    private static final String PATH_FXML_DIR = "/graphic/";

    /** The root node of this component. */
    private final T root;


    /**
     * Constructs a UiComponent by loading the FXML file in the specified
     * location.
     *
     * @param filePath - the String path of the FXML file to load in the
     *      resource folder relative to the fxml directory.
     */
    public UiComponent(String filePath) {
        this.root = initialiseRoot(filePath);
        assert root != null : "Root is not initialised";
    }


    /**
     * Loads the FXML file in the specified path String, setting the controller
     * as this object.
     *
     * @param filePath - the path String to the FXML file relative to the FXML
     *      directory folder.
     * @throws RuntimeException if an error occurs while loading the file.
     */
    private T initialiseRoot(String filePath) {
        try {
            FXMLLoader loader = new FXMLLoader(FileUtils.getResourceUrl(PATH_FXML_DIR + filePath));
            loader.setController(this);
            loader.setRoot(null);
            return loader.load();
        } catch (IOException ioEx) {
            throw new RuntimeException("Failed to load component fxml", ioEx);
        }
    }


    /**
     * Returns the root node of this component.
     *
     * @return the root node of this component.
     */
    public T getRoot() {
        return root;
    }
}
