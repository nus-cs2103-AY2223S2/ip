package duke;

import duke.command.Command;

import duke.exception.DukeException;

public class Duke {

    private static final String dirPath = "./data/";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showGreeting();
        this.ui.showSeparator();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showArrow();
                String fullCommand = this.ui.readCommand();
                ui.showSeparator();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showSeparator();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke(Duke.dirPath).run();
    }

}
