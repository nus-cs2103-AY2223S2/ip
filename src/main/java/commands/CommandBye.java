package commands;

import java.util.Scanner;

import features.TaskList;
import features.Ui;

/**
 * Handles 'bye' command.
 */
public class CommandBye extends Command {

    /**
     * Prints goodbye message.
     */
    public void print() {
        Ui ui = new Ui();
        ui.print("Goodbye, then!");
    }

    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) {
        return new TaskList();
    }
}
