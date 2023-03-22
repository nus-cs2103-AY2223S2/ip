package brotherbot;

import brotherbot.commands.Command;
import brotherbot.exceptions.BroException;
import brotherbot.parser.Parser;
import brotherbot.storage.Storage;
import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;
import javafx.application.Application;
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

public class BrotherBot extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image brother = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/brother.png")));


    public BrotherBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        storage.load(this.ui);
        this.tasks = storage.getTasks();
    }

    /**
     * Runs the BrotherBot application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, this.tasks);
                c.execute(this.tasks, this.ui);
                isExit = c.isExit;
                storage.save(c);
            } catch (BroException e) {
                ui.showError(e);
            }

        }
    }

    /**
     * Launches the BrotherBot application.
     */
    public static void main(String[] args) {
        new BrotherBot("data.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);


        //Step 2. Formatting the window to look as expected
        stage.setTitle("BrotherBot");
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));



        // more code to be added here later
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Iteration 1:
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBrotherDialog(dukeText, new ImageView(brother))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input, this.tasks);
            c.execute(this.tasks, this.ui);
            boolean isExit = c.isExit;
            storage.save(c);
        } catch (BroException e) {
            ui.showError(e);
        }
        return input;
    }


}

