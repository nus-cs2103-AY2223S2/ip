package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDos;

public class Duke extends Application {
    final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Storage storage;
    private final UI ui;

    public Duke() {
        this.storage = new Storage("duke.txt");
        this.ui = new UI();
        ui.showWelcome();
    }

    public static void main(String[] args) {
        // ...
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

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
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

        //interact
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
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

    public void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        int len = input.length();
        int i;
        Parser parser = new Parser();
        String command = parser.parse(input);
        ArrayList<Task> taskList = storage.load();
        String res = "";
        try {
            switch (command) {
            case "BYE":
                return "Bye. Hope to see you again soon!";
            case "LIST":
                if (taskList.size() == 0) {
                    String errMsg = "You have not upload any task yet";
                    throw new DukeException(errMsg);
                }
                res = "Here are the tasks in your list: \n";
                i = 1;
                for (Task task : taskList) {
                    res += i + ". " + task.toString() + "\n";
                    i++;
                }
                return res;
            case "DELETE":
                try {
                    int num = Integer.parseInt(input.substring(7));
                    Task currTask = taskList.get(num - 1);
                    taskList.remove(currTask);
                    storage.update_data(taskList);
                    return "Noted. I've removed this task: \n  " + currTask.toString() + "\n"
                            + "Now you have " + (taskList.size()) + " tasks in the list";
                } catch (IndexOutOfBoundsException err1) {
                    String errMsg = "☹ OOPS!!! Please the Duke.Task number that you have keyed in is invalid.";
                    return errMsg;
                } catch (NumberFormatException err2) {
                    String errMsg = "☹ OOPS!!! Please key in a valid Number.";
                    return errMsg;
                }
            case "MARK":
                try {
                    int num = Integer.parseInt(input.substring(5));
                    Task currTask = taskList.get(num - 1);
                    currTask.markAsDone();
                    taskList.set(num - 1, currTask);
                    storage.update_data(taskList);
                    return "Nice! I've marked this task as done\n" + currTask.getStatusIcon() + " " + currTask.getDes();
                } catch (IndexOutOfBoundsException err1) {
                    String errMsg = "☹ OOPS!!! Please the Duke.Task number that you have keyed in is invalid.";
                    return errMsg;
                } catch (NumberFormatException err2) {
                    String errMsg = "☹ OOPS!!! Please key in a valid Number.";
                    return errMsg;
                }
            case "UNMARK":
                try {
                    int num = Integer.parseInt(input.substring(7));
                    Task currTask = taskList.get(num - 1);
                    currTask.unMark();
                    taskList.set(num - 1, currTask);
                    storage.update_data(taskList);
                    return "OK, I've marked this task as not done yet\n"
                            + currTask.getStatusIcon() + " " + currTask.getDes();
                } catch (IndexOutOfBoundsException err1) {
                    String errMsg = "☹ OOPS!!! Please the Duke.Task "
                            + "number that you have keyed in is invalid.";
                    return errMsg;
                } catch (NumberFormatException err2) {
                    String errMsg = "☹ OOPS!!! Please key in a valid Number.";
                    return errMsg;
                }
            case "TODO":
                if (len <= 5) {
                    return "☹ OOPS!!! The description of a todo cannot be empty";
                }
                System.out.println("Got it. I've added this task:");
                ToDos todo = new ToDos(input.substring(5), 0);
                taskList.add(todo);
                storage.update_data(taskList);
                return "added: " + todo + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list";
            case "DEADLINE":
                String[] ddlStringArr = input.split(" /");
                if (len <= 9 || ddlStringArr.length <= 1 || ddlStringArr[0].length() < 10) {
                    String errMsg = "☹ OOPS!!! The description or date of a deadline cannot be empty";
                    throw new DukeException(errMsg);
                }
                try {
                    LocalDate deadlineTime = LocalDate.parse(ddlStringArr[1]);
                    Deadline deadline = new Deadline(ddlStringArr[0].substring(9), deadlineTime, 0);
                    taskList.add(deadline);
                    storage.update_data(taskList);
                    return "added: " + deadline + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list";
                } catch (DateTimeParseException e) {
                    String errMsg = "☹ OOPS!!! The description or date of a "
                            + "deadline is wrong, plase key in the"
                            + "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                            + "You may key in: deadline hw1 /2001-02-10, "
                            + "Duke.Duke will record your deadline for hw1 as"
                            + "2001-02-10";
                    throw new DukeException(errMsg);
                }
            case "EVENT":
                String[] eventStringArr = input.split(" /");
                if (len <= 9 || eventStringArr.length <= 2 || eventStringArr[0].length() < 7) {
                    String errMsg = "☹ OOPS!!! The description or date of a event cannot be empty";
                    throw new DukeException(errMsg);
                }
                try {
                    LocalDate from = LocalDate.parse(eventStringArr[1]);
                    LocalDate to = LocalDate.parse(eventStringArr[2]);
                    if (from.isAfter(to)) {
                        String errMsg = "☹ OOPS!!! Your time range is from a date to another date that is earlier"
                                + "than the former. Please key in a valid time range";
                        throw new DukeException(errMsg);
                    }
                    Event event = new Event(eventStringArr[0].substring(6), from, to, 0);
                    taskList.add(event);
                    storage.update_data(taskList);
                    return "Got it. I've added this task \n" + "added: " + event + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list";
                } catch (DateTimeParseException e) {
                    String errMsg = "☹ OOPS!!! The description or date for the "
                            + "event is wrong, plase key in the"
                            + "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                            + "You may key in: event hw1 /2001-02-10 /2001-02-12,"
                            + " Duke.Duke will record your event hw1 as"
                            + "from 2001-02-10 to 2001-02-12";
                    throw new DukeException(errMsg);
                }
            case "FIND":
                String[] fileInputArr = input.split(" ");
                if (taskList.size() == 0) {
                    String errMsg = "You have not upload any task yet";
                    throw new DukeException(errMsg);
                }
                res = "Here are the matching tasks in your list:\n";
                i = 1;
                for (Task task : taskList) {
                    if (task.getDescription().contains(fileInputArr[1])) {
                        res += i + ". " + task + "\n";
                        i++;
                    }
                }
                if ( i == 1 ) {
                    return res + "There are no matching task";
                }
                return res;
            case "ERROR":
                String errMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means";
                throw new DukeException(errMsg);
            default:
                break;
            }
        } catch (DukeException e) {
            return e.toString();
        }
        return "I don't know what that means";
    }
}