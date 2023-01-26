package duke.command;

import java.util.Scanner;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner scanner = new Scanner(command[1]);
            if (!scanner.hasNextInt()) {
                scanner.close();
                throw new DukeException(null, null);
            }
            int index = scanner.nextInt() - 1;
            if (tasks.get(index) == null) {
                scanner.close();
                throw new DukeException(null, null);
            }
            tasks.get(index).unmarkTask();
            ui.unmarkMsg(tasks, index);
            storage.saveToDisk(tasks);
            scanner.close();
        } catch (DukeException e) {
            ui.unmarkError();
        }
    }
    
}