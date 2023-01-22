package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList(this.ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        // print welcome message
        this.ui.showWelcome();

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
            }
        }
    }
}
