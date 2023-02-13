package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
/**
 * Main class of the program.
 * Contains the Storage, TaskList, Ui that will be used
 * by the Duke program.
 *
 * @author Cheam Jia Wei
 */
public class Duke {
    private final Storage store;
    private final TaskList taskList;
    private final Ui inter;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String path = System.getProperty("user.home") + "/data/duke.txt";
        this.store = new Storage(path);
        this.inter = new Ui();
        this.taskList = new TaskList();
        try {
            this.store.loadTasks(this.taskList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to run Duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.inter, this.store);
        } catch (IllegalArgumentException e) {
            return "Unrecognised command. Try again.";
        } catch (DateTimeParseException e) {
            return "Key in date and time in this format. yyyy-mm-ddThh:mm";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
