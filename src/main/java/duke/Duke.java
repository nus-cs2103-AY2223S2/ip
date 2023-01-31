package duke;

import duke.command.Command;
import duke.exceptions.TaskException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Acts as the main class for execution of user inputs.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Initialises classes need to read and write the program.
     *
     * @param filePath file name in the form of string
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, this.ui);
        this.tasks = new TaskList(this.storage, this.ui);
        this.parser = new Parser();
    }

    /**
     * Allow users to add, mark and un-mark, delete, add task (to-do, deadline, event)
     * or show items in a list and will exit if the bye command is returned
     */
    public String userInputs(String input) {
        Ui.welcomeMessage();
        this.storage.loadFileData();

        try {

            Command c = this.parser.parse(input);

            boolean isExit = c.isExit();
            this.storage.writeToFile();
            return c.execute(tasks, storage, ui);

        } catch (TaskException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return "Object pointing to null, please check code";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Check if the index is within the size of the array";
        } catch (IndexOutOfBoundsException e) {
            return "Nothing to mark/unmark!";
        }

    }

}
