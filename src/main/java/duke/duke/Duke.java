package duke.duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * The main class of the programme.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList list;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = this.storage.loadFile();
        } catch (DukeException e) {
            this.ui.display(e.getMessage());
            temp = new TaskList();
        }
        this.list = temp;

    }

    public static void main(String[] args) {
        String txtDir = System.getProperty("user.dir") + "/data/tasks.txt";

        Duke instance = new Duke(txtDir);
        instance.run();
    }

    /**
     * Runs the Duke programme.
     */
    public void run() {
        ui.showLogo();
        ui.showWelcome();

        Parser parser = new Parser();
        boolean isBye = false;
        while (!isBye) {
            try {
                String[] line = ui.readLine();
                Command c = parser.parse(line);
                c.execute(list, ui, storage);
                isBye = c.isBye();
            } catch (Exception err) {
                ui.showError(err);
            }
        }
    }
}
