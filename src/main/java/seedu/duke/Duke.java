package seedu.duke;

import java.util.ArrayList;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * default constructor if there is no existing file
     * @throws IOException
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage("duke.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a Duke object and initializes the needed parameters.
     *
     * @param filePath file path of the tasks file.
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() throws DukeException, IOException {
        storage.close();
    }

    public static void main(String[] args) throws DukeException, IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, DukeException {
        initializeComponents();

        Scene scene = new Scene(createLayout());
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Display welcome message
        Label welcomeMessage = new Label(ui.showWelcome());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke)));
    }

    private void initializeComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
    }

    private AnchorPane createLayout() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        return mainLayout;
    }

    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if(userInput.getText().equals("bye")) {
            Platform.exit();
        }
        userInput.clear();


    }

    /**
     * Generates a response to user input.
     *
     * @param input String containing the user input.
     * @return String containing the response from Duke.
     */
    private String getResponse(String input) throws IOException {
        String reply = "";
        try {
            reply = ui.getInput(tasks, input);
            storage.updateFile(tasks);
        } catch (DukeException | IOException e) {
            reply = e.getMessage();
        }
        if(reply.equals("    Bye. Hope to see you again soon!")) {
            storage.close();
        }
        return reply;
    }
}