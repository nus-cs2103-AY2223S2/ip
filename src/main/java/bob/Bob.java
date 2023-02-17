package bob;

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

/** Bob is a chat bot that helps manage tasks */
public class Bob extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bob = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    /**
     * Creates an instance of Bob which writes to a file with the path "data/taskList.txt"
     */
    public Bob() {
        storage = new Storage("data/taskList.txt");
        ui = new Ui("-", 30);
        tasks = new TaskList();
    }

    private String handleList() {
        return ui.printTasks(tasks.getList());
    }

    private String handleTodo(String input) throws BobException {
        Task t = Parser.parseTodo(input);
        tasks.add(t);
        return ui.printTaskAdded(t);
    }

    private String handleEvent(String input) throws BobException {
        Task t = Parser.parseEvent(input);
        tasks.add(t);
        return ui.printTaskAdded(t);
    }

    private String handleDeadline(String input) throws BobException {
        Task t = Parser.parseDeadline(input);
        tasks.add(t);
        return ui.printTaskAdded(t);
    }

    private String handleMark(String input) throws BobException {
        int index = Parser.parseIndex(input);
        tasks.mark(index);
        return ui.printMarkTask(tasks.get(index));
    }

    private String handleUnmark(String input) throws BobException {
        int index = Parser.parseIndex(input);
        tasks.unmark(index);
        return ui.printUnmarkTask(tasks.get(index));
    }

    private String handleDelete(String input) throws BobException {
        int index = Parser.parseIndex(input);
        return ui.printDeleteTask(tasks.delete(index));
    }

    private String handleFind(String input) throws BobException {
        String keyword = Parser.parseFind(input);
        return ui.printFilteredTasks(tasks.find(keyword));
    }

    private String handleRemind() {
        return ui.printReminders(tasks.getReminders());
    }

    private String handleInput(String input) throws BobException {
        String response;
        if (input.equals("list")) {
            response = handleList();
        } else if (input.equals("remind")) {
            response = handleRemind();
        } else if (input.startsWith("todo")) {
            response = handleTodo(input);
        } else if (input.startsWith("event")) {
            response = handleEvent(input);
        } else if (input.startsWith("deadline")) {
            response = handleDeadline(input);
        } else if (input.startsWith("mark")) {
            response = handleMark(input);
        } else if (input.startsWith("unmark")) {
            response = handleUnmark(input);
        } else if (input.startsWith("delete")) {
            response = handleDelete(input);
        } else if (input.startsWith("find")) {
            response = handleFind(input);
        } else { // Invalid command
            throw new BobException("No valid command was entered!");
        }
        return response;
    }

    private String getResponse(String input) {
        String response;
        try {
            response = handleInput(input);
        } catch (BobException e) {
            response = ui.errorPrint(e);
        }
        assert response != null;
        return response;
    }

    private void loadTasks() {
        try {
            storage.load(tasks);
        } catch (BobException e) {
            DialogBox bob = showBobDialog(ui.errorPrint(e));
            dialogContainer.getChildren().add(bob);
        }
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (BobException e) {
            DialogBox bob = showBobDialog(ui.errorPrint(e));
            dialogContainer.getChildren().add(bob);
        }
    }

    private DialogBox showUserDialog(String text) {
        Label userText = new Label(text);
        return DialogBox.getUserDialog(userText, new ImageView(user));
    }

    private DialogBox showBobDialog(String text) {
        assert text != null;
        Label bobText = new Label(text);
        return DialogBox.getBobDialog(bobText, new ImageView(bob));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox user = showUserDialog(input);
        DialogBox bob = showBobDialog(getResponse(input));

        dialogContainer.getChildren().addAll(
                user,
                bob
        );
        
        userInput.clear();

        // Save task list to data file after every input
        saveTasks();
    }

    // JavaFX setup
    private void setup(Stage stage) {
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

        format(stage, mainLayout);

        // Setting event handlers
        setHandlers();
    }

    // Format JavaFX
    private void format(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Bob");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);
        stage.getIcons().add(bob);

        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.setStyle("-fx-background-color: #89D08E");

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
    }


    private void setHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void introduce() {
        Label intro = new Label(ui.printIntroduction());
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(intro, new ImageView(bob))
        );
    }

    /**
     * Main program for Bob, our chat-bot
     */
    @Override
    public void start(Stage stage) {
        setup(stage);

        // Set introduction message
        introduce();

        // Load task list from file
        loadTasks();
    }
}
