package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        //Get 'duke.task.Deadline' description and 'by' index
        int byStartIndex = -1;

        for (int i = 0; i < splitted.length; i++) {
            String curString = splitted[i];

            if (curString.equals("/by")) {
                byStartIndex = i;
            }
        }

        //Handle no 'by' in instruction
        if (byStartIndex == -1) {
            throw new DukeBadInstructionFormatException("Usage of deadline: " +
                    "deadline [description] /by[date]");
        }

        //Make description and by string
        String[] descriptionArray = Arrays.copyOfRange(splitted, 1, byStartIndex);
        String[] byArray = Arrays.copyOfRange(splitted, byStartIndex + 1,
                splitted.length);
        String description = String.join(" ", descriptionArray);
        String by = String.join(" ", byArray);

        //Handle no description for a duke.task.Deadline
        if (description.equals("")) {
            throw new DukeBadInstructionFormatException(
                    "The description of a deadline cannot be empty.");
        }

        //Make duke.task.Deadline
        Deadline current_task = new Deadline(description, by);
        storage.fileAppend(current_task);
        tasks.append(current_task);
        ui.showAddedTask(current_task, tasks);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
