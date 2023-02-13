package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.views.UI;

/**
 * duke.Main driver class for duke.Duke.
 */
public class Duke {
    private UI ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * A constructor class for a Duke object.
     * @throws IOException When an error occurs while loading local file storage.
     */
    public Duke() throws IOException {
        this.tasks = new TaskList();
        this.ui = new UI();
        this.storage = new Storage(tasks, "/duke.txt");
        storage.load();
    }

    /**
     * Takes in input from the user, and passes it to Parser
     * to get the appropriate command and execute it.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(ui, tasks, storage);
            return c.getCommandStatus();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets what Duke should initially output to the user on program start up.
     * @return A string consisting of initialization messages.
     */
    public String getDukeInitMessage() {
        return this.ui.getInitMessage()
                + "\n \n"
                + this.ui.getAvailableCommands();
    }

}
