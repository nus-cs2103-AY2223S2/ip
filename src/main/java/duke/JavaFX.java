package duke;

import duke.parser.Parser;
import duke.ui.DialogBox;
import duke.ui.Ui;
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
 * Deals with the UI that the user sees
 *
 * @author He Shuimei
 * @version 0
 */
public class JavaFX extends Application {
    private Duke duke = new Duke("data\\save.txt");
    private final Ui ui = new Ui();
    private final Image userImg = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImg = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaDuke.png")));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Puke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Set up anything for duke
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.printWelcomeMessage()), new ImageView(dukeImg))
        );

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Handles users input by parsing the method
     * Prints a response to the user via a chat box
     */
    private void handleUserInput() {
        Parser parser = new Parser(duke.getTaskList(), duke.getStorage());
        Label userText = new Label(userInput.getText());
        String response = "";

        String input = userInput.getText().trim();
        String command = input.contains(" ") ? input.split(" ")[0] : input;
        String body = input.substring(command.length()).trim();

        try {
            response = parser.parseCommand(command, body);
            if (command.equals("bye")) {
                stop();
                Platform.exit();
            }
        } catch (Exception e) {
            response = ui.ERROR_EXCEPTION_CAUGHT + e;
        } finally {
            Label dukeText = new Label(response);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(userImg)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
            );
            userInput.clear();
        }
    }
}
