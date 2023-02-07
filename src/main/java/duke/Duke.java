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
     * unmarking, listing, finding.
     * @param input The user input
     * @return The output of the chatbot.
     */
    public String run(String input) {
        Command inputType = Parser.parse(input);
        assert inputType != null : "inputType should be known";
        String output;
        try {
            switch (inputType) {
            case LIST:
                output = tasks.listTasks();
                storage.writeData();
                return output;
            case FIND:
                output = tasks.findTasks(Parser.contents(input));
                storage.writeData();
                return output;
            case MARK:
                output = tasks.markTask(Parser.contents(input));
                storage.writeData();
                return output;
            case UNMARK:
                output = tasks.unmarkTask(Parser.contents(input));
                storage.writeData();
                return output;
            case DELETE:
                output = tasks.deleteTask(Parser.contents(input));
                storage.writeData();
                return output;
            case UNKNOWN:
                throw new DukeException("unknown");
            case BYE:
                break;
            default:
                output = tasks.addTask(inputType, Parser.contents(input));
                storage.writeData();
                return output;
            }
        } catch (DukeException e) {
            if (e.getMessage().equals("index")) {
                return "OOPS!!! Index out of range.";
            } else if (e.getMessage().equals("unknown")) {
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            } else {
                return "OOPS!!! The description of a " + e.getMessage() + " cannot be empty.";
            }
        } catch (DateTimeParseException e) {
            return "Incorrect format detected." + "\nPlease enter date/time in the following format:"
                    + "\n    yyyy-MM-dd HHmm";
        }

        storage.writeData();
        return "Bye. Hope to see you soon!";
    }
}
