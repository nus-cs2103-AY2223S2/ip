package book.gui;

import book.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField inputField;

    private Book book;

    private Image bookIcon = new Image(this.getClass().getResourceAsStream("/images/Book_Icon.png"));
    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/User_Icon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

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
        dialogContainer.getChildren().addAll(
                new DialogBox(userIcon, input),
                new DialogBox(bookIcon, response)
        );
        inputField.clear();
    }
}
