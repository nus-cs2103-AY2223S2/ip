package duke.ui;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.data.TaskList;
import duke.parser.Parser;
import duke.task.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * duke.ui.Ui class represents User interface when using the chatbox.
 */
public class Ui {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private Button sendButton;
    private VBox dialogContainer;

    /**
     * Initializes an Ui object represent the user interface.
     */
    public Ui() {
        this.scrollPane = new ScrollPane();
        this.sendButton = new Button();
        this.dialogContainer = new VBox();
    }

    /**
     * Display the intro of chatbot
     */
    public void displayIntro() {
        String introMessage = "Hello! I'm Duke\n What can I do for you?";
        Label intro = new Label(introMessage);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(intro, new ImageView(duke))
        );
    }

    /**
     * Display a in-app help page.
     *
     * @return The response message for the help command.
     */
    public static String getHelpResponse() {
        String helpResponse = "This is a list of my features and commands that you can use:\n"
                + "1. todo [task name]: add a To Do task to the list.\n"
                + "2. deadline [task name] \\by [yyyy-MM-dd] : add a Deadline task to the list.\n"
                + "3. event [task name] \\from [yyyy-MM-dd] \\to [yyyy-MM-dd] : add an event task to the list.\n"
                + "4. list : to display the task list currently in order.\n"
                + "5. mark [task number] : to mark a task with the number from the list order.\n"
                + "6. unmark [task number] : to unmark a task with the number from the list order.\n"
                + "7. delete [task number] : to delete a task with the number from the list order.\n"
                + "8. find [keyword] : find any tasks contain that keyword.\n"
                + "9. bye : to end our sweet sweet conversation <3.\n"
                + "10. help : to see a list of commands which you are seeing right neowwwww.\n";
        return helpResponse;
    }

    /**
     * Returns the outro message of the chatbot.
     *
     * @return The outro message of the chatbot.
     */
    public static String getOutroMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the tasks list stored in the bot.
     *
     * @param taskList A duke.data.TaskList Object encapsulating the all tasks in the chatbot.
     * @return The message of the chatbot to list all tasks.
     */
    public static String getTaskListMessage(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            return "You currently don't have any task in my list.";
        }
        String tasksList = "";
        Task currentTask;
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            tasksList += i + 1 + "." + currentTask.toString() + "\n";
        }
        return "Here are the tasks in your list:\n" + tasksList;
    }

    /**
     * Prints a message corresponding to the adding action of argument task.
     *
     * @param newTask The new task being added.
     * @param size    The size of task list after adding.
     * @return The message from chatbot after adding a task.
     */
    public static String getAddTaskMessage(Task newTask, int size) {
        return "Got it. I've added this task:\n  "
                + newTask
                + "\nNow you have " + size + " tasks in the list";
    }

    /**
     * Prints a message corresponding to the marking action of the argument task.
     *
     * @param target The task being marked.
     * @return The message of the chatbot after marking a task.
     */
    public static String getMarkTaskMessage(Task target) {
        return "Nice! I've marked this task as above:\n  " + target;
    }

    /**
     * Prints a message corresponding to the unmarking action of the argument task.
     *
     * @param target The task being unmarked.
     * @return The message of the chatbot after unmarking a task.
     */
    public static String getUnmarkTaskMessage(Task target) {
        return "OK, I've marked this task as not done yet:\n  " + target;
    }

    /**
     * Prints a message corresponding to the deleting action of the argument task.
     *
     * @param target The duke.task.Task being deleted from the list.
     * @param size   The current size of task list.
     * @return The message of the chatbot after deleting a task.
     */
    public static String getDeleteTaskMessage(Task target, int size) {
        return "Noted. I've removed this task:\n"
                + target + "\nNow you have " + size + " task(s) in the list";

    }

    /**
     * Prints a message corresponding to the finding action with the list of task found.
     *
     * @param resultedTasks A Stream of Task object encapsulating all tasks found.
     * @return The message of the chatbot after finding tasks by a keyword
     */
    public static String getFindTaskMessage(Stream<Task> resultedTasks) {
        Stream<String> searchResult = resultedTasks.map((task) -> task.toString());
        String listingResultMessage = searchResult.reduce("", (result, element) -> result + element + "\n");
        return "Here are the matching tasks in your list:\n" + listingResultMessage;
    }

    /**
     * Takes in a stage object from overriding start() method from Duke class and set some default
     * attributes to it.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Set the Scroll Pane in the user interface to default.
     */
    public void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Set the Anchor Pane in the user interface to default.
     *
     * @param userInput A TextField object with input user keyed in.
     */
    public void setAnchorPane(TextField userInput) {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Takes in the user input from a TextField object and a Parser of chatbot
     * Do the corresponding action and display it on the user interface.
     *
     * @param userInput
     * @param parser
     */
    public void manageGuiEchoing(TextField userInput, Parser parser) {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //handle user input.
        sendButton.setOnMouseClicked((event) -> {
            parser.handleUserInput(userInput, dialogContainer, new ImageView(user), new ImageView(duke));
        });

        userInput.setOnAction((event) -> {
            parser.handleUserInput(userInput, dialogContainer, new ImageView(user), new ImageView(duke));
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Button getSendButton() {
        return this.sendButton;
    }
}
