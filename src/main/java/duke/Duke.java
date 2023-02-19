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

    private String taskTaggedMessage(Task task) {
        return "Got it. I've tagged this task:\n" +
                task.toString() + "\n";
    }

    private Message handleDisplayCommand(String[] tokens) throws DukeException {
        assert(tokens[0].equals("list") || tokens[0].equals("find"));
        switch(tokens[0]) {
        case "list":
            if (tokens.length == 1) {
                return new Message(taskList.getItemListAsResponseString());
            } else throw new DukeException("command \"list\" takes no arguments");

        case "find":
            List<Integer> indexList = taskList.getMatchingItemsIndices(tokens);
            return new Message(taskList.getItemListAsResponseString(indexList));

        default:
            throw new DukeException("broken display command");
        }
    }

    private Message handleEditCommand(String[] tokens) throws DukeException {
        assert(tokens[0].equals("mark") || tokens[0].equals("unmark") || tokens[0].equals("tag"));
        switch(tokens[0]) {
        case "mark":
            Task updatedTask = taskList.markListItem(tokens);
            return new Message("Nice! I've marked this task as done:\n"
                    + updatedTask.toString() + "\n");

        case "unmark":
            Task unmarkedTask = taskList.unmarkListItem(tokens);
            return new Message("OK, I've marked this task as not done yet:\n"
                    + unmarkedTask.toString() + "\n");

        case "tag":
            Task taggedTask = taskList.addTag(tokens);
            return new Message(taskTaggedMessage(taggedTask));

        default:
            throw new DukeException("broken edit command");
        }
    }

    private Message handleAddCommand(String[] tokens) throws DukeException {
        assert(tokens[0].equals("todo") || tokens[0].equals("deadline") || tokens[0].equals("event"));
        switch(tokens[0]) {
        case "todo":
            Task addedTodoTask = taskList.addToDo(tokens);
            return new Message(taskAddedMessage(addedTodoTask));

        case "deadline":
            Task addedDeadlineTask = taskList.addDeadline(tokens);
            return new Message(taskAddedMessage(addedDeadlineTask));

        case "event":
            Task addedEventTask = taskList.addEvent(tokens);
            return new Message(taskAddedMessage(addedEventTask));

        default:
            throw new DukeException("broken add command");
        }
    }

    private Message handleDeleteCommand(String[] tokens) throws DukeException {
        switch(tokens[0]) {
        case "delete":
            Task removedTask = taskList.deleteItem(tokens, ui);
            return new Message("Noted. I've removed this task:\n" +
                    removedTask +
                    "\nNow you have " + taskList.size() + " tasks in the list\n");

        default:
            throw new DukeException("broken delete command");
        }
    }

    private Message handleUnknownCommand(String[] tokens) {
        return new Message("unknown command\n");
    }

    /**
     * Returns a <code>Message</code> that represents the response of the chatbot when prompted by an input by user.
     * @param msg A <code>Message</code> object representing the user's request
     * @return A <code>Message</code> object containing the response of the bot to the user's request
     */
    public Message respondToMessage(Message msg) throws DukeException {
        assert(msg != null);
        String input = msg.getMessage();
        String[] tokens = parser.parseUserInput(input);

        if (tokens.length == 1 && tokens[0].equals("bye")) {
            exit();
            return new Message("This message should never show up");

        }

        String command = tokens[0];
        switch (command) {
        case "list":
        case "find":
            return handleDisplayCommand(tokens);
        case "mark":
        case "unmark":
        case "tag":
            return handleEditCommand(tokens);
        case "todo":
        case "deadline":
        case "event":
            return handleAddCommand(tokens);
        case "delete":
            return handleDeleteCommand(tokens);
        default:
            return handleUnknownCommand(tokens);
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
