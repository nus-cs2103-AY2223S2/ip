package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private static final String PATH = "/data/duke.txt";

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String path) {
        this.ui = new Ui();
        this.taskList = new TaskList(this.ui);
        this.storage = new Storage(path, this.ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke(PATH);
        // print welcome message
        duke.storage.readToTaskList(duke.taskList);
        duke.ui.showWelcome();
        duke.run();
    }

    public void run() {

        // parse user input
        String rawInput;
        Command command;
        boolean isExit = false;

        while (!isExit) {
            try {
                // scan for user input
                rawInput = this.ui.readCommand();
                command = Parser.parse(rawInput);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.addToMessage(e.toString());
            } finally {
                this.ui.displayMessage();
                this.storage.saveToFile(this.taskList);
            }
        }
    }
}
