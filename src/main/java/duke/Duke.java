package duke;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.ui.UiController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

/**
 * The main driver class for Duke chatbot. This bot handles high level logic for the bot including startup and
 * shutdown.
 */
public class Duke extends Application {
    private final UiController ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;


    /**
     * Primary constructor for an instance of the chatbot. This initialises the Ui, taskList, storage and parser
     * instances used by the bot, which are not to be modified throughout the lifecycle of the bot.
     */
    public Duke() {
        this.ui = new UiController(this);
        this.taskList = new TaskList();
        this.storage = new Storage("src/data/duke.txt", this.taskList);
        this.parser = new Parser();
    }

    /**
     * Terminates the current instance of the chatbot by exiting the program after displaying exit message.
     */
    void exit() {
        storage.updateData(this.taskList);
        ui.displayGoodbyeMessage();
        System.exit(0);
    }

    private String taskAddedMessage(Task task) {
        return "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " +
                taskList.size() +
                " tasks in the list\n";
    }

    /**
     * Returns a <code>Message</code> that represents the response of the chatbot when prompted by an input by user.
     * @param msg A <code>Message</code> object representing the user's request
     * @return A <code>Message</code> object containing the response of the bot to the user's request
     */
    public Message respondToMessage(Message msg) {
        assert(msg != null);
        String input = msg.getMessage();
        String[] tokens = parser.parseUserInput(input);

        if (tokens.length == 1 && tokens[0].equals("bye")) {
            exit();
            return new Message("This message should never show up");

        } else if (tokens.length == 1 && tokens[0].equals("list")) {
            return new Message(taskList.getItemListAsResponseString());

        } else if (tokens[0].equals("mark")) {
            try {
                Task updatedTask = taskList.markListItem(tokens);
                return new Message("Nice! I've marked this task as done:\n"
                        + updatedTask.toString() + "\n");
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("unmark")) {
            try {
                Task updatedTask = taskList.unmarkListItem(tokens);
                return new Message("OK, I've marked this task as not done yet:\n"
                        + updatedTask.toString() + "\n");
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("todo")) {
            try {
                Task addedTask = taskList.addToDo(tokens);
                return new Message(taskAddedMessage(addedTask));
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("deadline")) {
            try {
                Task addedTask = taskList.addDeadline(tokens);
                return new Message(taskAddedMessage(addedTask));
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("event")) {
            try {
                Task addedTask = taskList.addEvent(tokens);
                return new Message(taskAddedMessage(addedTask));
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("delete")) {
            try {
                Task removedTask = taskList.deleteItem(tokens, ui);
                return new Message("Noted. I've removed this task:\n" +
                        removedTask +
                        "\nNow you have " + taskList.size() + " tasks in the list\n");
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else if (tokens[0].equals("find")) {
            try {
                List<Integer> indexList = taskList.getMatchingItemsIndices(tokens);
                return new Message(taskList.getItemListAsResponseString(indexList));
            } catch (DukeException e) {
                return new Message(e.getMessage());
            }

        } else {
            return new Message("unknown command\n");
        }
    }

    /**
     * {@inheritDoc}
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        ui.initUiElems(stage);
        ui.displayWelcomeMessage();
    }
}
