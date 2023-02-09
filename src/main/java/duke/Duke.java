package duke;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;

public class Duke {
    Ui ui = new Ui();
    TaskList taskList = new TaskList();
    Parser parser = new Parser();
    Storage store = new Storage(taskList);

    public String getGreeting() {
        return ui.printGreeting();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert !input.isEmpty();
        try {
            String message = parser.dispatch(input, ui, taskList);
            store.saveTasks(taskList);
            return message;
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }
}
