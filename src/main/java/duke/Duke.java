package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Duke program, a chatbot that helps you keep track of your tasks.
 */
public class Duke {
    private Ui ui;
    private final Storage storage;
    private TaskList tl;
    private boolean isExit;

    private Duke(String filePath) {
        this.isExit = false;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tl = this.storage.load();
        } catch (DukeException e) {
            this.ui.showToUser(e.getMessage());
        }
    }

    /**
     * Main method to run the Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String txtDir = "./save.txt";
        new Duke(txtDir).run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (!this.isExit) {
            try {
                String[] commandString = ui.getUserCommand();
                Parser parser = new Parser();
                Command c = parser.parse(commandString);
                c.execute(tl, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            }
        }
    }
}

