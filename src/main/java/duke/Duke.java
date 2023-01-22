package duke;

import command.Command;
import dukeException.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Duke() {
        this.storage = new Storage("./data/taskList.txt");
        this.taskList = new TaskList();
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.storage.create();
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the Duke chatbot.
     */
    public void run() {
        this.ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
