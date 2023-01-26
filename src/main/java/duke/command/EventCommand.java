package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

public class EventCommand extends Command {

    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        //Get 'duke.task.Deadline' description, 'from' and 'to' index
        int fromStartIndex = -1;
        int toStartIndex = -1;

        for (int i = 0; i < splitted.length; i++) {
            String curString = splitted[i];

            if (curString.equals("/from")) {
                fromStartIndex = i;
            }

            else if (curString.equals("/to")) {
                toStartIndex = i;
            }
        }
        //Handle invalid from or to start index
        if (fromStartIndex == -1 || toStartIndex == -1 ||
                fromStartIndex > toStartIndex) {
            throw new DukeBadInstructionFormatException("Usage of duke.task.Event: " +
                    "event [description] /from[date] /to[date]");
        }

        //Make description and by string
        String[] descriptionArray = Arrays.copyOfRange(splitted, 1, fromStartIndex);
        String[] fromArray = Arrays.copyOfRange(splitted, fromStartIndex + 1,
                toStartIndex);
        String[] toArray = Arrays.copyOfRange(splitted, toStartIndex + 1,
                splitted.length);
        String description = String.join(" ", descriptionArray);
        String from = String.join(" ", fromArray);
        String to = String.join(" ", toArray);

        //Handle no description for an duke.task.Event
        if (description.equals("")) {
            throw new DukeBadInstructionFormatException(
                    "The description of an event cannot be empty.");
        }

        //Make duke.task.Event
        Event current_task = new Event(description, from, to);
        storage.fileAppend(current_task);
        tasks.append(current_task);
        ui.showAddedTask(current_task, tasks);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
