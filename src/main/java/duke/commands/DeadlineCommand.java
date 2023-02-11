package duke.commands;

import duke.utils.DukeIo;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

/**
 * DeadlineCommand adds a Deadline object to TaksList.
 */
public class DeadlineCommand extends Command {
    private final String[] TOKENS;

    /**
     * Public constructor for Deadline command.
     * @param tokens Tokenised user inputs from user.
     */
    public DeadlineCommand(String[] tokens) {
        this.TOKENS = tokens;
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
        assert TOKENS.length == 2;
        String desc = TOKENS[0];
        String byDeadline = TOKENS[1];
        return new Deadline(desc, byDeadline);
    }
}
