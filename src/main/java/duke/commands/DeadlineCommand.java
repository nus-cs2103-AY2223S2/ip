package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * DeadlineCommand adds a Deadline object to TaksList.
 */
public class DeadlineCommand extends Command {
    private final String[] tokens;

    /**
     * Public constructor for Deadline command.
     * @param tokens Tokenised user inputs from user.
     */
    public DeadlineCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Method to add Deadline task into TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        Deadline d = createDeadline();
        tasks.addTask(d);
        return dukeIo.notifySuccessAdd(d) + dukeIo.showCount();
    }

    private Deadline createDeadline() {
        assert tokens.length == 2;
        String desc = tokens[0].trim();
        String byDeadline = tokens[1].trim();
        return new Deadline(desc, byDeadline);
    }
}
