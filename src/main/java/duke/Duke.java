package duke;

import duke.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String SAVE_PATH = "./tasklist.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasklistFromFile());
        } catch (DukeException e) {
            // ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_PATH);
        duke.run();
    }

    public void run() {
        commandLoop();
    }

    public void commandLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                // ui.showError(e.getMessage());
            } finally {
                // ui.showLine();
            }
        }

        // printInBanner("Otsunakiri~", "Byebye!~");
    }
}
