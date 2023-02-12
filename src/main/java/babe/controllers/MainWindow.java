package babe.controllers;

import babe.Babe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Babe babe;

    private Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image BABE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/babe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBabe(Babe b) {
        babe = b;
    }

    public void welcomeUser() {
        dialogContainer.getChildren().addAll(DialogBox.getBabeDialog(this.babe.welcomeUser(), BABE_IMAGE));

        String initStorageResults = this.babe.initializeStorage();
        if (!initStorageResults.isEmpty()) {
            dialogContainer.getChildren().addAll(DialogBox.getBabeDialog(initStorageResults, BABE_IMAGE));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), USER_IMAGE),
                DialogBox.getBabeDialog(babe.getResponse(userInput.getText()), BABE_IMAGE)
        );
        userInput.clear();
    }
}