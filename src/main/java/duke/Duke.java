package duke;
import java.io.IOException;

import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Represents the chatbot
 */
public class Duke extends Application {
    private static String FILE_PATH = "./data/duke.txt";
    private TaskList list;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Command dukeCommand;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/dukeUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeBot.png"));

    /**
     * Initializes a Duke object
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.list = new TaskList(this.storage.loadData());
        this.parser = new Parser();
        this.dukeCommand = new Command("fail", this.list);
    }

    protected String getResponse(String input) {
        String response = null;
        String[] userInput = this.parser.parseUserInput(input);
        String dukeQuery = userInput[0];

        assert dukeQuery != null;
        this.dukeCommand = new Command(dukeQuery, this.list);
        response = this.dukeCommand.executeCommand(this.list, userInput);

        try {
            this.storage.storeData(this.list);
        } catch (IOException e) {
            response = "Error while storing data..";
        }

        assert response != null;
        return response;
    }


    /**
     * Creates a Duke object
     * @param args
     */
    public static void main(String[] args) {
        Duke dukeObj = new Duke();
        dukeObj.ui.initialDisplay();
    }

    /**
     * Creates layout for gui
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(585, 735);
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

}
