package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * The main class of the Duke Task Manager application.
 * This class is responsible for starting the application,
 * initializing the task list, and handling user inputs.
 * @author @tricixg
 * @version 1.0
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/spot.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/arlo.jpeg"));

    @Override
    public void start(Stage stage) {
        storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");
        tasks = new TaskList(storage.load());
        Color color = Color.rgb(202, 231, 193);
        CornerRadii radius = new CornerRadii(5);
        Background greenBackground = new Background(new BackgroundFill(color, radius, Insets.EMPTY));

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setBackground(greenBackground);
        scrollPane.setContent(dialogContainer);
        scrollPane.setBackground(greenBackground);
        userInput = new TextField();
        userInput.setPromptText("task Watch The Good Dino /by Tomorrow"); //to set the hint text
        userInput.requestFocus(); //to not setting the focus on that node so that the hint will display immediately
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setBackground(greenBackground);
        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Broccoli");
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
        String greet = Ui.getGreeting();
        Label greeting = new Label(greet);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, new ImageView(duke)));
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        String input = userInput.getText().trim();
        String[] splitInput = input.split("\\s+");
        String response = Parser.parseInput(splitInput);

        Label userText = new Label(input);
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (input.contains("bye")) {
            storage.saveToFile(tasks);
            Platform.exit();
        }
    }
}



