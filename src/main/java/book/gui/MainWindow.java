package book.gui;

import book.Book;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** Welcome message for Book. */
    private static final String WELCOME_MESSAGE = "Good day, this is Book!\nWhat may I help you with?";
    /** The {@code ScrollPane} instance associated with the GUI. */
    @FXML
    private ScrollPane scrollPane;
    /** The {@code VBox} container instance associated with the GUI. */
    @FXML
    private VBox dialogContainer;
    /** The {@code TextField} instance for input associated with the GUI. */
    @FXML
    private TextField inputField;
    /** The {@code Book} instance associated with the GUI. */
    private Book book;
    /** The {@code Image} resource for Book's icon. */
    private Image bookIcon = new Image(this.getClass().getResourceAsStream("/images/Book_Icon.png"));
    /** The {@code Image} resource for a user icon. */
    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/User_Icon.png"));

    /**
     * Initializes the {@code MainWindow}.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(new DialogBox(bookIcon, WELCOME_MESSAGE));
    }

    /**
     * Associates an instance of {@code Book} with the {@code MainWindow}.
     * @param book The instance of {@code Book} to associate with the {@code MainWindow}.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = inputField.getText();
        String response = book.parseAndReturn(inputField.getText());
        this.dialogContainer.getChildren().addAll(
                new DialogBox(userIcon, input),
                new DialogBox(bookIcon, response)
        );
        this.inputField.clear();
    }
}
