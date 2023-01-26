package jarvis;

import java.util.Objects;
import java.util.Scanner;

import jarvis.command.Command;
import jarvis.exception.InvalidActionException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import jarvis.ui.message.MessageBox;
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

/**
 * Jarvis class to run the program.
 */
public class Jarvis extends Application {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();

    private final Image user = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/Jarvis.jpg")));
    private final Image jarvis = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/User.png")));

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    private boolean isExit;

    private VBox chatContainer;
    private TextField userInput;

    /**
     * Constructor for Jarvis.
     */
    public Jarvis() {
        this.storage = new Storage();
        this.ui = new Ui(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());

        this.isExit = false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScrollPane scrollPane = new ScrollPane();
        chatContainer = new VBox();
        chatContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        chatContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1));

        scrollPane.setPrefSize(400, 600);
        scrollPane.setContent(chatContainer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        userInput.setPrefWidth(320);
        userInput.setOnAction(event -> onUserInput());

        Button sendButton = new Button("Enter");
        sendButton.setPrefWidth(60);
        sendButton.setOnMouseClicked(event -> onUserInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400, 600);
        mainLayout.getChildren().addAll(
                scrollPane,
                userInput,
                sendButton
        );

        AnchorPane.setTopAnchor(scrollPane, 1.);

        AnchorPane.setBottomAnchor(userInput, 1.);
        AnchorPane.setLeftAnchor(userInput, 1.);

        AnchorPane.setBottomAnchor(sendButton, 1.);
        AnchorPane.setRightAnchor(sendButton, 1.);

        Scene scene = new Scene(mainLayout);

        primaryStage.setTitle("Jarvis");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runs Jarvis.
     */
    public void run() {
        this.ui.printLogo();
        this.ui.printStandard(Ui.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (!isExit && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            this.handleUserInput(line);
        }
        scanner.close();
    }

    private void onUserInput() {
        if (userInput.getText().isBlank()) {
            return;
        }

        this.handleUserInput(userInput.getText());
        Label inputText = new Label(userInput.getText());
        Label jarvisText = new Label(this.ui.dumpResponses());
        chatContainer.getChildren().addAll(
                new MessageBox(inputText, new ImageView(user)),
                new MessageBox(jarvisText, new ImageView(jarvis)).flip()
        );
        userInput.clear();
    }

    private void handleUserInput(String input) {
        if (input == null || input.isBlank()) {
            this.ui.printUserPrompt();
            return;
        }

        Command command;
        try {
            command = Parser.parse(input);
        } catch (InvalidActionException e) {
            this.ui.printStandard(Ui.Response.CONFUSED);
            return;
        }
        command.execute(this.ui, this.taskList, this.storage);
        isExit = command.isExit();
    }
}
