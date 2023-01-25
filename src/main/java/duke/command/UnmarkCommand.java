package main.java.duke.command;

import java.util.Scanner;

import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;
import main.java.duke.exception.DukeException;
import main.java.duke.task.TaskList;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner sc = new Scanner(command[1]);
            if (!sc.hasNextInt()) {
                sc.close();
                throw new DukeException(null, null);
            }
            int index = sc.nextInt() - 1;
            if (tasks.get(index) == null) {
                sc.close();
                throw new DukeException(null, null);
            }
            tasks.get(index).unmarkTask();
            ui.unmarkMsg(tasks, index);
            storage.write(tasks);
            sc.close();
        } catch (DukeException e) {
            ui.unmarkError();
        }
    }
    
}