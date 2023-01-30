package duke;

import duke.exceptions.DukeException;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

/**
 * The main driver class for Duke chatbot. This bot handles high level logic for the bot including startup and
 * shutdown.
 */
public class Duke extends Application {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;


    /**
     * Primary constructor for an instance of the chatbot. This initialises the Ui, taskList, storage and parser
     * instances used by the bot, which are not to be modified throughout the lifecycle of the bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage("src/data/duke.txt", this.taskList);
        this.parser = new Parser();
    }

    /**
     * Terminates the current instance of the chatbot by exiting the program.
     */
    void exit() {
        storage.updateData(this.taskList);
        ui.displayMessage("Bye. Hope to see you again soon!\n");
        System.exit(0);
    }

    public String getResponse(String input) {
        String[] tokens = parser.parseUserInput(input);

        if (tokens.length == 1 && tokens[0].equals("bye")) {
            exit();//todo

        } else if (tokens.length == 1 && tokens[0].equals("list")) {
            return taskList.getItemListAsResponseString();

        } else if (tokens[0].equals("mark")) {
            try {
                String updatedTaskString = taskList.markListItem(tokens, ui);
                return "Nice! I've marked this task as done:\n" + updatedTaskString;
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("unmark")) {
            try {
                String updatedTaskString = taskList.unmarkListItem(tokens, ui);
                return "OK, I've marked this task as not done yet:\n" + updatedTaskString;
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("todo")) {
            try {
                taskList.addToDo(tokens, ui);
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("deadline")) {
            try {
                taskList.addDeadline(tokens, ui);
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("event")) {
            try {
                taskList.addEvent(tokens, ui);
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("delete")) {
            try {
                String removedTaskString = taskList.deleteItem(tokens, ui);
                return "Noted. I've removed this task:\n" +
                        removedTaskString +
                        "\nNow you have " + taskList.size() + " tasks in the list\n";
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else if (tokens[0].equals("find")) {
            try {
                List<Integer> indexList = taskList.getMatchingItemsIndices(tokens);
                return taskList.getItemListAsResponseString(indexList);
            } catch (DukeException e) {
                return e.getMessage();
            }

        } else {
            return "unknown command\n";
        }
    }

    @Override
    public void start(Stage stage) {
        Pane mainLayout = ui.initUiElems(stage);
        ui.setLayout(stage, mainLayout);
        ui.setEventListeners(this);
    }

    public static void main(String[] args) {
        new Duke();
    }
}
