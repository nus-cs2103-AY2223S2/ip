package duke;

import java.util.List;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class implements a personal assistant chatbot that helps the user to keep track of various tasks.
 *
 * @author Chia Jeremy
 */
public class Duke {

    public static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a new Duke instance and populate it with values from the storage file if it exists.
     * Otherwise, create a new empty Duke instance.
     *
     * @param filePath the path to the file
     */
    public Duke(String filePath) {
        assert pathNotEmpty(filePath);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
        this.ui = new Ui();
    }

    /**
     * Returns the greeting message when Duke starts.
     *
     * @return the greeting message
     */
    public String getGreeting() {
        return this.ui.greet();
    }

    /**
     * Returns different Duke's responses depending on user input.
     *
     * @param input the user input
     * @return the Duke's response
     */
    public String getResponse(String input) {
        Command command = new Parser().parseCommand(input);
        command.execute(this.storage, this.tasks, this.ui);
        return ui.getResponse();
    }

    public List<Task> getTasksToDisplay() {
        return ui.getTasksToDisplay();
    }

    private Boolean pathNotEmpty(String path) {
        return !path.isEmpty();
    }
}
