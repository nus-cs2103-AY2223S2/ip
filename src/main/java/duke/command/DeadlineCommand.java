package duke.command;

import duke.storage.StorageList;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deadline command for the tasks with deadline.
 */
public class DeadlineCommand extends Command {
    private String message;
    private String timing;

    /**
     * Constructor for deadline command
     *
     * @param fullCommand Command for the deadline task
     */
    public DeadlineCommand(String fullCommand) {
        String[] checker = fullCommand.split("/by ");
        String[] checkerDeadline = checker[0].split("deadline ");
        this.message = checkerDeadline[1];
        this.timing = checker[1];
    }

    /**
     * Execute method for deadline command to display message for deadline and warn users
     * of formatting.
     *
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return String
     */
    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        Deadline taskDeadline = new Deadline(message, timing);
        if (!taskDeadline.checkFormat()) {
            tasks.addToList(taskDeadline);
            return "Got it, I've added this task:\n" + taskDeadline + tasks.statement();
        } else {
            return "Wrong Format, Please fill in with the following format: YYYY-MM-DD h:mm";
        }
    }

}

