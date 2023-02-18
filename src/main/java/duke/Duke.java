package duke;

import duke.commands.Command;
import duke.database.Database;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * DukeBot that contains all the logic and calls the relevant methods based on the commands given.
 */
public class Duke {

    private static final String FRAME = ""; //"    ____________________________________________________________\n";
    private final Database database;
    private TaskList tasks;
    private Ui ui;
    private boolean isActive;
    private final Parser parser;


    /**
     * Represents the Duke chatbot wrapped with all its necessary dependencies such as
     * Ui, Database, TaskList and Parser.
     *
     * @param filePath path of the database file.
     */
    public Duke(String filePath) {
        this.database = new Database(filePath);
        this.isActive = true;
        this.parser = new Parser();
        try {
            tasks = new TaskList(database.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }


    /**
     * Runs the Duke chatbot and activates the UI to receive and display information
     */
    public void runCommand(String command) {
        try {
            Command nextCommand = this.parser.parse(command, this.tasks.length());
            nextCommand.execute(this.tasks, this.ui, this.database);
            this.isActive = nextCommand.isActive();
        } catch (DukeException e) {
            ui.response(e.getLocalizedMessage());
        } finally {
            ui.showResponse();
            if (!this.isActive) {
                Platform.exit();
            }
        }
    }


    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
