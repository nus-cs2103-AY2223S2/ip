package duke;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Sender;
import duke.parser.Parser;
import duke.exceptions.DukeException;

/**
 * A chat bot program.
 */
public class Duke {
    /** Parses user input. */
    private Parser parser;

    /** The path to the file that stores the Tasks on the hard drive. */
    private String FILE_PATH = "duke.txt";

    /**
     * Constructs a new Duke session.
     */
    public Duke() {
           Sender sender = new Sender();
           Storage storage = new Storage(FILE_PATH);
            //Attempts to load Tasks from hard drive into the list of Tasks.
            TaskList tasks = new TaskList(storage.load());
            this.parser = new Parser(sender, storage, tasks);
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


