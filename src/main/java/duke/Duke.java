package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class Duke {
    private UserInterface ui;
    private TaskList list;
    private Parser parser;

    public Duke() {
        ui = new UserInterface();
        list = new TaskList();
        parser = new Parser();

    }

    public void run() {
        ui.showGreeting();
        while (true) {
            String input = ui.getInput();
            try {
                parser.parse(input).execute(list, ui);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }

    }
}
