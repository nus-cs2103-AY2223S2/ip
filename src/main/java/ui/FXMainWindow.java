package ui;

import javafx.scene.layout.AnchorPane;

/**
 * A subclass of AnchorPane that represents the main chat window.
 * @author Nicholas Lee
 */
public class FXMainWindow extends AnchorPane {

    private final FXChatPane chatPane;

    public FXMainWindow(FXChatPane chatPane, FXInputBox inputBox) {
        this.chatPane = chatPane;
        setTopAnchor(chatPane, 1.0);
        setRightAnchor(chatPane, 1.0);
        setLeftAnchor(chatPane, 1.0);
        setBottomAnchor(inputBox, 1.0);
        setRightAnchor(inputBox, 1.0);
        setLeftAnchor(inputBox, 1.0);
        this.getChildren().addAll(chatPane, inputBox);
        this.setPrefSize(385, 535);
    }

    /**
     * Creates an instance of FXMainWindow representing the main chat application window.
     * @param chatPane The chat window JavaFX node
     * @param inputBox The input box Java FX node
     * @return A new instance of FXDialogBox with the specified label and a cropped version of the image.
     */
    public static FXMainWindow getMainWindow(FXChatPane chatPane, FXInputBox inputBox) {
        return new FXMainWindow(chatPane, inputBox);
    }

    /**
     * Adds a dialogue between the user and the bot to the ChatPane component
     * @param input The user input.
     * @param botResponse The bot response.
     */
    public void addDialogue(String input, String botResponse) {
        this.chatPane.addChatToChatPane(input, botResponse);
    }
}
