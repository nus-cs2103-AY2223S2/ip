package bob;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public Bob() {
        storage = new Storage("data/taskList.txt");
        ui = new Ui("~", 30);
        tasks = new TaskList();
    }

    private String getResponse(String input) {
        String response;
        try {
            int index;
            Task t;

            if (input.equals("list")) {
                response = ui.printTasks(tasks.getList());
            } else if (input.startsWith("todo")) {
                t = Parser.parseTodo(input);
                tasks.add(t);
                response = ui.printTaskAdded(t);
            } else if (input.startsWith("event")) {
                t = Parser.parseEvent(input);
                tasks.add(t);
                response = ui.printTaskAdded(t);
            } else if (input.startsWith("deadline")) {
                t = Parser.parseDeadline(input);
                tasks.add(t);
                response = ui.printTaskAdded(t);
            } else if (input.startsWith("mark")) {
                index = Parser.parseIndex(input);
                response = ui.printMarkTask(tasks.get(index));
            } else if (input.startsWith("unmark")) {
                index = Parser.parseIndex(input);
                tasks.unmark(index);
                response = ui.printUnmarkTask(tasks.get(index));
            } else if (input.startsWith("delete")) {
                index = Parser.parseIndex(input);
                response = ui.printDeleteTask(tasks.delete(index));
            } else if (input.startsWith("find")) {
                String keyword = Parser.parseFind(input);
                response = ui.printFilteredTasks(tasks.find(keyword));
            } else { // Invalid command
                throw new BobException("No valid command was entered!");
            }
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
