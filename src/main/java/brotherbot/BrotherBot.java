package brotherbot;

import brotherbot.commands.Command;
import brotherbot.exceptions.BroException;
import brotherbot.parser.Parser;
import brotherbot.storage.Storage;
import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;
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

public class BrotherBot extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image brother = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/brother.png")));


    public BrotherBot() {
        this.ui = new Ui();
        this.storage = new Storage("data.txt");
        this.tasks = storage.getTasks();
    }

    /**
     * Runs the BrotherBot application.
     */

    /**
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(fullCommand, this.tasks);
                c.execute(this.tasks);
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
    /**
    public static void main(String[] args) {
        new BrotherBot("data.txt").run();
    }
**/
    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("SEND");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);


        //Step 2. Formatting the window to look as expected
        stage.setTitle("BrotherBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(440.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
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

        // load Welcome message
        Label load = new Label("Welcome to Brother Bot - your one-stop Personal Task Planner with a very 'bro' personality!\nHello my brother, what can I do for you mi amigo...\n Loading prev files: \n" + storage.load());
        dialogContainer.getChildren().addAll(
                DialogBox.getBrotherDialog(load, new ImageView(brother))
        );

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String output = getResponse(userInput.getText());
        Label broText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBrotherDialog(broText, new ImageView(brother))
        );
        userInput.clear();
        if (Objects.equals(output, "ok see you brother all love no cringe!")) {
            Platform.exit();
        }
    }

    private String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input, this.tasks);
            output = c.execute(this.tasks);
            storage.save(c);
            return output;
        } catch (BroException e) {
            output = e.getMessage();
            return output;
        }
    }
}

