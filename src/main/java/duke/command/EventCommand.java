package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'event' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class EventCommand extends Command {
    /**
     * Constructor for an instance of an <code>EventCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>EventCommand</code>, adding an <code>Event</code>
     * to tasks.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is not 'clear'
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        assert splitted[0].equals(Parser.EVENT_STRING) : "Wrong command made an event";
        //Get 'duke.task.Deadline' description, 'from' and 'to' index
        int fromStartIndex = -1;
        int toStartIndex = -1;

        for (int i = 0; i < splitted.length; i++) {
            String curString = splitted[i];

            if (curString.equals("/from")) {
                fromStartIndex = i;
            } else if (curString.equals("/to")) {
                toStartIndex = i;
            }
        }
        //Handle invalid from or to start index
        boolean isInvalidFromOrTo = fromStartIndex == -1 || toStartIndex == -1
                || fromStartIndex > toStartIndex;
        if (isInvalidFromOrTo) {
            throw new DukeBadInstructionFormatException("Usage of duke.task.Event: "
                    + "event [description] /from[date] /to[date]");
        }

        //Make description and from and to string
        String description = Command.getTaskDescription(splitted, fromStartIndex);
        String from = Command.getEventFrom(splitted, fromStartIndex, toStartIndex);
        String to = Command.getEventToOrDeadlineBy(splitted, toStartIndex);

        //Handle no description for an duke.task.Event
        if (description.equals("")) {
            throw new DukeBadInstructionFormatException(
                    "The description of an event cannot be empty.");
        }

        //Make duke.task.Event
        Event currentTask = new Event(description, from, to);
        storage.fileAppend(currentTask);
        tasks.append(currentTask);
        return ui.showAddedTask(currentTask, tasks);

    }
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
