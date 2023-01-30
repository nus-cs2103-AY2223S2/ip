package duke;

import duke.commands.Command;
import duke.exceptions.*;

public class Duke {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage.loadState(tasks);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        ui.sayBye();
    }
}
