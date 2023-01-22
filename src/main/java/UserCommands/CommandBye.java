package UserCommands;

import Features.TaskList;
import Features.Ui;

import java.util.Scanner;

public class CommandBye extends Command {

    public void print() {
        Ui ui = new Ui();
        ui.print("Goodbye, then!");
    }

    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) {
        return new TaskList();
    }
}
