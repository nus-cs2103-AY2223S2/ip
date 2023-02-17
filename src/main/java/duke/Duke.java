package duke;

import java.io.IOException;

import duke.helpers.TaskList;
import duke.helpers.UI;

/**
 * Main Duke class file. Sets up UI and loads up saved tasks if any.
 * Continues querying the user until bye command is entered.
 *
 * @author jengoc415
 */
public class Duke {

    private TaskList taskList;
    private UI ui;

    public Duke() throws IOException {
        this.ui = new UI(new TaskList());
    }

    public String getGreeting() {
        return this.ui.greeting();
    }

    public String getResponse(String input) throws IOException {
        return ui.process(input);
    }
}
