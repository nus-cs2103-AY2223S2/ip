package io.gui;

import java.io.IOException;

import command.Command;
import command.Error;
import io.Storage;
import io.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import task.TaskList;

/**
 * Graphical User Interface
 */
public class Gui extends Application {
    // private static final double WIN_WIDTH = 400.0;
    // private static final double WIN_HEIGHT = 600.0;
    // private static final double USER_INP_HEIGHT = 35.0;
    // private static final double DIALOGUE_HEIGHT = WIN_HEIGHT - USER_INP_HEIGHT -
    // 30.0;
    // private static final double DIALOGUE_WIDTH = WIN_WIDTH;
    // private static final double BUTTON_HEIGHT = USER_INP_HEIGHT;
    // private static final double BUTTON_WIDTH = 55.0;
    // private static final double USER_INP_WIDTH = WIN_WIDTH - BUTTON_WIDTH;
    // private static final double SCROLL_WIDTH = 15.0;
    // private static final double PADDING = 10.0;
    // private static final double OFFSET = 1.0;
    // private static final String TITLE = "D";
    // private static final boolean IS_RESIZABLE = false;
    // private static final String BUTTON_CONTENT = ">>>";

    // private static final String WELCOME = "Hello. My name is D.\nI am a simple
    // task manager, designed to serve humanity.\nHere are your current tasks: %s";

    // private ScrollPane scrollPane;
    // private VBox dialogueContainer;
    // private TextField userInput;
    // private Button sendButton;
    // private Scene scene;
    // private AnchorPane mainLayout;

    // private TaskList taskList;
    // private Storage<TaskList> storage;

    // private final Image userImg = new
    // Image(this.getClass().getResourceAsStream("/images/default_user_pic.png"));
    // private final Image dImg = new
    // Image(this.getClass().getResourceAsStream("/images/cyborg_girl.png"));

    // private void loadTasks() {
    // storage = Storage.of(TaskList.class, "taskList.ser");
    // taskList = storage.load().match(
    // lst -> lst,
    // error -> {
    // switch (error) {
    // case FILE_NOT_FOUND:
    // return new TaskList();
    // case IO_ERROR:
    // case CAST_ERROR:
    // showReply("Error loading tasks, tasks have been reset");
    // return new TaskList();
    // default:
    // return new TaskList();
    // }
    // });
    // }

    // @Override
    // public void start(Stage stage) {
    // // Setup
    // this.mainLayout = new AnchorPane();
    // this.scrollPane = new ScrollPane();
    // this.dialogueContainer = new VBox();
    // this.userInput = new TextField();
    // this.sendButton = new Button(BUTTON_CONTENT);

    // this.scrollPane.setContent(this.dialogueContainer);

    // // Window formatting
    // stage.setTitle(TITLE);
    // stage.setResizable(IS_RESIZABLE);
    // stage.setHeight(WIN_HEIGHT);
    // stage.setWidth(WIN_WIDTH);
    // mainLayout.setPrefSize(WIN_WIDTH, WIN_HEIGHT);

    // // Scrollpane formatting
    // scrollPane.setPrefHeight(DIALOGUE_HEIGHT - SCROLL_WIDTH);
    // scrollPane.setPrefWidth(DIALOGUE_WIDTH - SCROLL_WIDTH);
    // scrollPane.setVvalue(OFFSET);
    // scrollPane.setFitToWidth(true);
    // scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    // dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    // dialogueContainer.heightProperty().addListener(observable ->
    // scrollPane.setVvalue(OFFSET));
    // dialogueContainer.setSpacing(PADDING);

    // // User Input Box formatting
    // userInput.setPrefHeight(USER_INP_HEIGHT);
    // userInput.setPrefWidth(USER_INP_WIDTH);
    // sendButton.setPrefHeight(BUTTON_HEIGHT);
    // sendButton.setPrefWidth(BUTTON_WIDTH);

    // // Putting all together
    // AnchorPane.setTopAnchor(scrollPane, OFFSET);
    // AnchorPane.setBottomAnchor(userInput, OFFSET);
    // AnchorPane.setLeftAnchor(userInput, OFFSET);
    // AnchorPane.setBottomAnchor(sendButton, OFFSET);
    // AnchorPane.setRightAnchor(sendButton, OFFSET);
    // mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    // this.scene = new Scene(this.mainLayout);
    // stage.setScene(scene);
    // stage.show();

    // loadTasks();
    // showReply(String.format(WELCOME, taskList.toString()));

    // // User input
    // sendButton.setOnMouseClicked((event) -> {
    // handleUserInput();
    // });

    // userInput.setOnAction((event) -> {
    // handleUserInput();
    // });
    // }

    // /**
    // * Tries to save taskList if window is closed without "bye" command.
    // */
    // @Override
    // public void stop() {
    // storage.save(taskList);
    // }

    // /**
    // * Obtains user input, creates appropriate command which is executed.
    // * User input added to the dialogueContainer as a DialogueBox.
    // */
    // private void handleUserInput() {
    // String userText = userInput.getText();
    // if (userText.isEmpty()) {
    // return;
    // }
    // dialogueContainer.getChildren().addAll(
    // DialogueBox.of(userText, new ImageView(userImg)));
    // userInput.clear();

    // Command command = Command.parser().parse(userText).match(
    // pr -> pr.first(),
    // msg -> Error.of(msg));
    // command.execute(taskList, this, storage);
    // if (command.isExit()) {
    // Platform.exit();
    // }
    // }

    // /**
    // * {@inheritDoc}}
    // */
    // @Override
    // public void showReply(String msg) {
    // dialogueContainer.getChildren().addAll(DialogueBox.of(msg, new
    // ImageView(dImg)));
    // }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(300);
            stage.setTitle("D");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Entry point into app
     */
    public static void launch(String... args) {
        Application.launch(Gui.class, args);
    }
}
