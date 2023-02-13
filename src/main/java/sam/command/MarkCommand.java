package sam.command;

import sam.Dialog;
import sam.Ui;
import sam.parser.Parser;
import sam.parser.SamInvalidIntException;
import sam.parser.SamInvalidTaskException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.SamMissingTaskException;
import sam.task.Task;
import sam.task.TaskList;

/**
 * Represents a user command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {
    private boolean isDone;

    /**
     * Constructs a new MarkCommand.
     *
     * @param args The command string.
     * @param isDone Indicates whether the task is done.
     */
    public MarkCommand(String args, boolean isDone) {
        super(args);
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws SamMissingTaskException, SamInvalidIntException,
            SamInvalidTaskException, SamSaveFailedException {
        if (args.isEmpty()) {
            throw new SamMissingTaskException();
        }
        int id = Parser.parseInt(args);
        Task task = tasks.setTaskDone(id, isDone);
        if (task == null) {
            throw new SamInvalidTaskException();
        }
        String message = isDone
                ? Dialog.MARK.getDialog()
                : Dialog.UNMARK.getDialog();
        ui.respond(message,
                task.toString());
        storage.save(tasks);
    }
}
