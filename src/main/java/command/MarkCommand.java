package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * The {@code MarkCommand} class implements the {@link Command} interface and
 * represents a command to mark a task in the task list as done.
 */
public class MarkCommand implements Command {
    private int index;

    /**
     Constructs a {@code MarkCommand} object with the specified task index.
     @param index the index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     Executes this command by marking the task with the specified index in the
     task list as done.
     @param ui the user interface to use for input/output
     @param list the task list
     @param storage the storage backend to use for persistence
     */
    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.markTaskAsDone(index);
    }

    /**
     Returns {@code false} to indicate that this command does not cause the
     application to exit.
     @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
