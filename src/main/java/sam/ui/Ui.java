package sam.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sam.Sam;

/**
 * Handles user interaction.
 */
public class Ui {
    private static final String LOGO =
              " ██████╗ █████╗ ███╗   ███╗\n"
            + "██╔════╝██╔══██╗████╗ ████║\n"
            + "╚█████╗ ███████║██╔████╔██║\n"
            + " ╚═══██╗██╔══██║██║╚██╔╝██║\n"
            + "██████╔╝██║  ██║██║ ╚═╝ ██║\n"
            + "╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝";
    private static final String USER =
              " ███████\n"
            + "████▀██▀█\n"
            + "████▄██▄█\n"
            + " ▀▀▀▀▀▀▀";
    private static final String SAM =
              "        ▄\n"
            + " ▒▒██▒▒▓▓▀\n"
            + "▒▒▀██▀▒▒▓▓\n"
            + " █▄██▄███▓▓\n"
            + "  ▀▀▀▀▀▀ ▓";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
    }

    /**
     * Sets up the application's stage.
     *
     * @param stage The stage to set up.
     */
    public void setStage(Stage stage) {
        double windowHeight = 600.0;
        double windowWidth = 400.0;
        double inputHeight = 60.0;
        double inputWidth = 340.0;
        double padding = 16.0;

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);

        // formatting

        stage.setTitle("Sam");
        stage.setResizable(false);
        stage.setMinHeight(windowHeight);
        stage.setMinWidth(windowWidth);

        mainLayout.setPrefSize(windowWidth, windowHeight);

        scrollPane.setPrefSize(windowWidth, windowHeight - inputHeight);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPadding(new Insets(padding));

        userInput.setPrefWidth(inputWidth);
        userInput.setPrefHeight(inputHeight);
        sendButton.setPrefWidth(windowWidth - inputWidth);
        sendButton.setPrefHeight(inputHeight);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // handle user input

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        showLogo();
        respond(Dialog.GREETING.getDialog());
    }

    /**
     * Reads the user input and issues a command to Sam.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label userChar = new Label(USER);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, userChar));

        Sam.getSamInstance().issueCommand(userInput.getText());
        userInput.clear();
    }

    /**
     * Displays the logo of the app.
     */
    public void showLogo() {
        Label logo = new Label(LOGO);
        logo.setStyle("-fx-font-family: 'monospaced';");
        dialogContainer.getChildren().add(logo);
    }

    /**
     * Adds a dialogue from Sam formed by the given strings.
     *
     * @param messages A list of strings representing lines of dialogue.
     */
    public void respond(String... messages) {
        StringBuilder str = new StringBuilder();
        for (String message : messages) {
            str.append(message + "\n");
        }
        Label samChar = new Label(SAM);
        Label samText = new Label(str.toString());
        dialogContainer.getChildren().add(DialogBox.getSamDialog(samText, samChar));
    }

    /**
     * Disables the input field and send button
     */
    public void disable() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
