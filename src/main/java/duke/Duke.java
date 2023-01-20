package duke;

import java.io.*;
import java.time.format.DateTimeParseException;

import duke.commands.*;

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
     *
     * @param path The relative file path where data will be stored into
     * @author Cheam Jia Wei
     */

    public Duke(String path) {
        this.store = new Storage(path);
        this.taskList = new TaskList();
        this.inter = new Ui();
        try {
            this.store.loadTasks(this.taskList);
        } catch (FileNotFoundException e) {
            this.inter.loadError();
        }
    }

    /**
     * Function to initialise and run Duke
     *
     * @author Cheam Jia Wei
     */
    public void start() {
        this.inter.greet();
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = this.inter.uiRead();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.inter, this.store);
                isExit = c.isExit();
            }
            catch (IllegalArgumentException e) {
                this.inter.printError("Unrecognised command. Try again.");
            } catch (DateTimeParseException e) {
                this.inter.printError("Key in date and time in this format. yyyy-mm-ddThh:mm");
            }
            catch (Exception e) {
                this.inter.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.home") + "/data/duke.txt";
        Duke duke = new Duke(path);
        duke.start();
    }
}
