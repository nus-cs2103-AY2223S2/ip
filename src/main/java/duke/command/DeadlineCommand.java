package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'deadline' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for an instance of a <code>DeadlineCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>DeadlineCommand</code>, adding a <code>Deadline</code>
     * to <code>tasks</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is wrong.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        assert splitted[0].equals(Parser.DEADLINE_STRING) : "Wrong command made a deadline";
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
            throw new DukeBadInstructionFormatException("Usage of deadline: "
                    + "deadline [description] /by[date]");
        }

        //Make description and by string
        String description = Command.getTaskDescription(splitted, byStartIndex);
        String by = Command.getEventToOrDeadlineBy(splitted, byStartIndex);

        //Handle no description for a duke.task.Deadline
        if (description.equals("")) {
            throw new DukeBadInstructionFormatException(
                    "The description of a deadline cannot be empty.");
        }

        //Make duke.task.Deadline
        Deadline currentTask = new Deadline(description, by);
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
