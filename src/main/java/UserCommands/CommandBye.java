package UserCommands;

import java.util.Scanner;

import Features.TaskList;
import Features.Ui;

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
