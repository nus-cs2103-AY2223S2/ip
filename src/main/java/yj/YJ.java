package yj;

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

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YJ extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.webp"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.webp"));
    public YJ(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Step 2. Formatting the window to look as expected
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
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Scene initializaton and rendering
        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        // UI test
        Application.launch(YJ.class, args);

        new YJ("tasks.txt").run();
    }

    public void run() {
        ui.print("Hello! I'm YJ. What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            Command command = Parser.parseCommand(input);
            switch (command) {
                case LIST:
                    tasks.forEachTask((task, i) -> ui.print((i + 1) + "." + task.toString()));
                    break;
                case DELETE:
                    try {
                        Integer taskNumber = Parser.parseDeleteCommand(input);
                        Task task = tasks.deleteTask(taskNumber);
                        ui.print("I've removed this task as u lazily requested:");
                        ui.print(task.toString());
                        ui.print("Now you have like this many tasks left: " + tasks.getNumberofTasks());
                    } catch (IndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify a task number or a valid task to delete.");
                    }
                    break;
                case MARK: {
                    Integer taskNumber = Parser.parseMarkCommand(input);
                    if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                        Task task = tasks.getTask(taskNumber - 1);
                        task.markAsDone();
                        ui.print("Niceoooo you're done wit this: ");
                        ui.print(task.toString());
                    }
                    break;
                }
                case UNMARK: {
                    Integer taskNumber = Parser.parseUnMarkCommand(input);
                    if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                        Task task = tasks.getTask(taskNumber - 1);
                        task.markAsNotDone();
                        ui.print("Ok lah you haven't finish this yet");
                        ui.print(task.toString());
                    }
                    break;
                }
                case TODO:
                    try {
                        String description = Parser.parseToDoCommand(input);
                        ToDo newToDo = new ToDo(description);
                        tasks.addTask(newToDo);
                        ui.print("Ok! I've added this todo! " + newToDo.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (IllegalArgumentException e) {
                        ui.print(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        Map<String, String> result = Parser.parseDeadlineCommand(input);
                        Deadline newDeadline = new Deadline(result.get("description"), result.get("by"));
                        tasks.addTask(newDeadline);
                        ui.print("Ok! I've added this deadline!" + newDeadline.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify a deadline in the correct format!");
                    }
                    break;
                case EVENT:
                    try {
                        Map<String, String> result = Parser.parseEventCommand(input);
                        Event newEvent = new Event(result.get("description"), result.get("from"), result.get("to"));
                        tasks.addTask(newEvent);
                        ui.print("Ok! I've added this event!" + newEvent.toString());
                        ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.print("Crapadoodle! You need to specify an event in the correct format!");
                    }
                    break;
                case BYE:
                    // Write tasks to file
                    storage.save(tasks.getTasks());
                    ui.print("Byebye, YJ will miss you :(");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    ui.print("Crapdoodledy, I don't know what that means man");

            }
            input = sc.nextLine();
        }
    }
}

