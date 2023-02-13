package sam.command;

import sam.parser.Parser;
import sam.parser.SamInvalidIntException;
import sam.parser.SamInvalidTaskException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.SamMissingTaskException;
import sam.task.Task;
import sam.task.TaskList;
import sam.ui.Dialog;

/**
 * Represents a user command to delete a task.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public Result execute(TaskList tasks, Storage storage)
            throws SamMissingTaskException, SamInvalidIntException,
            SamInvalidTaskException, SamSaveFailedException {
        if (args.isEmpty()) {
            throw new SamMissingTaskException();
        }
        int id = Parser.parseInt(args);
        Task task = tasks.removeTask(id);
        if (task == null) {
            throw new SamInvalidTaskException();
        }

        storage.save(tasks);

        result.addMessage(Dialog.DELETE.getDialog());
        result.addTask(task);
        return result;
    }
}
