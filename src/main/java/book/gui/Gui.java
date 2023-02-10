package book.gui;

import java.io.IOException;

import book.Book;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class that handles the GUI for user interaction.
 */
public class Gui extends Application {
    /** The {@code Book} instance associated with the GUI. */
    private Book book;

    /**
     * Constructor that initializes a {@code Gui} instance with its own {@code Book} instance.
     */
    public Gui() {
        this.book = new Book();
    }

    /**
     * Overrides the {@code start} method for JavaFx.
     *
     * @param stage the primary stage for this application, onto which the application scene can be
     *         set. Applications may create other stages, if needed, but they will not be primary
     *         stages.
     */
    @Override
    public void start(Stage stage) {
        assert this.book != null : "GUI should have an associated Book";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Book");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setBook(book);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
