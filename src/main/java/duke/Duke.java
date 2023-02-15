package duke;

import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.parser.Parser.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * A chatbot with functionality to add or remove different
 * types of Tasks.
 */
public class Duke {
    /** Duke.Storage component handling loading and writing tasks */
    private Storage storage;
    /** Task list with operations */
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath The location to load or save data from/to.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public Duke() {
        this("data/list.txt");
    }

    /**
     * Runs the main logic of the chatbot.
     * Supports 3 tasks: Todo, Event and Deadline.
     * Supports commands: adding, deletion, marking,
     * unmarking, listing, finding, updating.
     * @param input The user input
     * @return The output of the chatbot.
     */
    public String run(String input) {
        Command inputType = Parser.parse(input);
        assert inputType != null : "inputType should be known";
        return runCommand(inputType, input);
    }

    /**
     * Calls the relevant method in tasklist and returns
     * the String.
     * @param command The type of command.
     * @param input The string command from user.
     * @return The output of executing the command.
     */
    public String runCommand(Command command, String input) {
        String output;
        try {
            switch (command) {
            case LIST:
                output = tasks.listTasks();
                break;
            case FIND:
                output = tasks.findTasks(Parser.contents(input));
                break;
            case MARK:
                output = tasks.markTask(Parser.contents(input));
                break;
            case UNMARK:
                output = tasks.unmarkTask(Parser.contents(input));
                break;
            case DELETE:
                output = tasks.deleteTask(Parser.contents(input));
                break;
            case UNKNOWN:
                throw new DukeException("unknown");
            case BYE:
                output = "Meow. Hope to see you soon!";
                break;
            case UPDATE:
                output = tasks.updateTask(Parser.contents(input));
                break;
            default:
                output = tasks.addTask(command, Parser.contents(input));
                break;
            }
            storage.writeData();
            return output;
        } catch (DukeException e) {
            if (e.getMessage().equals("index")) {
                return "MEOW!!! Index out of range.";
            } else if (e.getMessage().equals("unknown")) {
                return "MEOW!!! I'm sorry, but I don't know what that means :-(";
            } else if (e.getMessage().equals("update")) {
                return "Incorrect format detected." + "\nPlease enter update in the following format:"
                        + "\n    update index description";
            } else {
                return "MEOW!!! The description of a " + e.getMessage() + " cannot be empty.";
            }
        } catch (DateTimeParseException e) {
            return "Incorrect format detected." + "\nPlease enter date/time in the following format:"
                    + "\n    yyyy-MM-dd HHmm";
        }
    }
}
