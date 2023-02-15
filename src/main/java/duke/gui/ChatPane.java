package duke.gui;

import duke.gui.handler.MessageHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * ChatPane is a javafx component that acts as the GUI interface for the user to interact with the underlying duke.
 */
public class ChatPane extends AnchorPane {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private MessageHandler messageHandler;

    /**
     * Initialises an instance of ChatPane.
     *
     * @param messageHandler handler that handles incoming messages from users.
     */
    public ChatPane(MessageHandler messageHandler) {
        initialise(messageHandler);
        style();
        setupActions();
    }

    /**
     * Displays a message from the user in the GUI.
     *
     * @param message Message to be displayed on the GUI.
     */
    public void userSays(String message) {
        Label userText = new Label(message);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));
    }

    /**
     * Displays a message from duke in the GUI.
     *
     * @param message Message to be displayed on the GUI.
     */
    public void dukeSays(String message) {
        Label dukeText = new Label(message);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    private void initialise(MessageHandler messageHandler) {
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);
        this.userInput = new TextField();
        this.sendButton = new Button("Send");
        this.messageHandler = messageHandler;
        getChildren().addAll(scrollPane, userInput, sendButton);
    }

    private void style() {
        assert scrollPane != null : "scrollPane not yet initialised";
        assert dialogContainer != null : "dialogContainer not yet initialised";
        assert userInput != null : "userInput not yet initialised";
        assert sendButton != null : "sendButton not yet initialised";
        setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(400, 550);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(400.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setupActions() {
        assert userInput != null : "userInput not yet initialised";
        assert sendButton != null : "sendButton not yet initialised";
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String cmd = userInput.getText();
        String resp = messageHandler.handle(cmd);
        userSays(cmd);
        dukeSays(resp);
        userInput.clear();
    }
}
