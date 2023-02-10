package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Represents the Duke task manager.
 * 
 * @author Samarth Verma
 */
public class Duke {
    private UserInterface ui;
    private TaskList list;
    private Parser parser;
    private Storage storage;

    /**
     * Creates a new Duke object.
     */
    public Duke() {
        ui = new UserInterface();
        parser = new Parser();
        
        list = new TaskList();
        Path filePath = Paths.get(".", "data", "duke.txt");
        storage = new Storage(filePath.toString());

        try {
            list = storage.read();
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Runs the Duke task manager.
     */
    public void run() {
        ui.showGreeting();
        while (true) {
            String input = ui.getInput();
            try {
                parser.parse(input).execute(list, ui, storage);
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }

    }
}
