package duke;
import duke.task.TaskList;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * A Duke class
 */
public class Duke extends Application {
    private static String[] spStg;
    private Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/scottie.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/scottie.jpg"));
    private Boolean isExit = false;

    /**
     * Initializes a duke task
     */
    public Duke() {
        ui = new Ui();
        try {
            tasks = new TaskList(Storage.loadFile());
        } catch (Exception e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (this.isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

    private String getResponse(String input) {
        return this.run(input);
    }

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

        Label userText = new Label(ui.showWelcome());
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user))
        );

        stage.setTitle("Duke");
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
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });
        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Run the duke instance
     */
    public String run(String fullCommand) {
        String firstWord = Parser.getFirstWord(fullCommand);
        switch (firstWord) {
        case "bye":
            Storage.saveToFile(tasks.getList());
            this.isExit = true;
            return ui.showBye();

        case "list":
            return tasks.list();

        case "view":
            return tasks.view(fullCommand);

        case "delete":
            return tasks.delete(Parser.getIndex(fullCommand));

        case "mark":
            return tasks.mark(Parser.getIndex(fullCommand));

        case "unmark":
            return tasks.unmark(Parser.getIndex(fullCommand));

        case "todo":
            return tasks.addTodo(fullCommand);

        case "deadline":
            return tasks.addDeadline(fullCommand);

        case "event":
            return tasks.addEvent(fullCommand);

        case "find":
            return tasks.find(fullCommand);

        default:
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}



