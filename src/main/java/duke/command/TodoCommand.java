package duke.command;

import java.util.Arrays;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {

    public TodoCommand(String fullCommand) {

        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length == 1) {
            throw new DukeBadInstructionFormatException(
                    "The description of a todo cannot be empty.");
        }

        //Get 'duke.task.ToDo' description
        String[] descriptionArray = Arrays.copyOfRange(splitted, 1, splitted.length);
        String description = String.join(" ", descriptionArray);

        //Make Todo
        ToDo current_task = new ToDo(description);
        storage.fileAppend(current_task);
        tasks.append(current_task);
        ui.showAddedTask(current_task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
