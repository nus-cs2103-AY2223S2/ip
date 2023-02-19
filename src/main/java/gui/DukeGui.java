package gui;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The class that starts the GUI application and interacts with the user.
 */
public class DukeGui extends Application {
    private static final String SEND_BUTTON_TEXT = "Send";

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.jpg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/duke.jpg")));
    private TextField userInput;
    private VBox dialogContainer;
    private final Duke duke = new Duke();

    /**
     * Starts the GUI.
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed,
     *              but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        // set stage title to Duke name
        String stageTitle = duke.getName();

        // set up GUI components
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        this.dialogContainer = dialogContainer;

        scrollPane.setContent(dialogContainer);
        TextField userInput = new TextField();
        this.userInput = userInput;

        Button sendButton = new Button(SEND_BUTTON_TEXT);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        // set stage parameters
        stage.setTitle(stageTitle);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // show welcome
        showWelcome();

        // step 3: handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // render
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (duke.isExited()) {
            Platform.exit();
        }
        Label userText = new Label(userInput.getText());

        Message dukeResponse = getResponse(userInput.getText());
        Label dukeText = new Label(dukeResponse.toString());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage), dukeResponse.getIsError())
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private Message getResponse(String input) {
        return duke.handleCommandWithException(input);
    }

    /**
     * Prints to GUI interface
     * @param string the string to be printed out
     */
    public void dukePrintNormalMessage(String string) {
        Label dukeText = new Label(string);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage), false)
        );
        userInput.clear();
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        dukePrintNormalMessage(duke.getWelcomeMessage());
    }
}
