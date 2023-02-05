package duke;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
    }

    public void run() {
        Parser parser = new Parser(this.storage, this.tasks, this.ui);
        ui.displayWelcomeMessage();
        while (ui.isRunning()) {
            try {
                String[] userInput = ui.readUserInput();
                parser.parse(userInput);
            } catch (Exception e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data.txt").run();
    }
}
