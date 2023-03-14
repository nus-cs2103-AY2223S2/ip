package duke;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.parser.Parser;
import duke.exceptions.DukeException;

/**
 * A chat bot program.
 */
public class Duke {
    /** Parses user input. */
    private Parser parser;

    /**
     * Constructs a new Duke session.
     */
    public Duke(String filePath) {
            Storage storage = new Storage(filePath);
            //Attempts to load Tasks from hard drive into the list of Tasks.
            TaskList tasks = new TaskList(storage.load());
            this.parser = new Parser(storage, tasks);
    }

    /**
     * Gets an appropriate response to the user input.
     * @param input the user's input
     * @return the response to the user
     */
    public String getResponse(String input) {
        try {
             String parsedOutput =  parser.parse(input);
             return parsedOutput;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


