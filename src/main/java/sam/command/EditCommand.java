package sam.command;

import java.util.HashMap;
import java.util.Map;

import sam.parser.Parser;
import sam.parser.SamInvalidDateException;
import sam.parser.SamInvalidIntException;
import sam.parser.SamInvalidTaskException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.SamMissingTaskArgException;
import sam.task.SamMissingTaskException;
import sam.task.SamMissingTaskValueException;
import sam.task.Task;
import sam.task.TaskList;
import sam.ui.Dialog;

/**
 * Represents a user command to edit a task.
 */
public class EditCommand extends Command {
    /**
     * Constructs a new EditCommand.
     *
     * @param args The command string.
     */
    public EditCommand(String args) {
        super(args);
    }

    @Override
    public Result execute(TaskList tasks, Storage storage)
            throws SamMissingTaskException, SamInvalidIntException,
            SamMissingTaskArgException, SamMissingTaskValueException,
            SamInvalidDateException, SamInvalidTaskException, SamSaveFailedException {
        String input = args.strip();
        if (args.isEmpty()) {
            throw new SamMissingTaskException();
        }
        String[] idArgs = input.split(" +/", 2);
        int id = Parser.parseInt(idArgs[0]);

        Map<String, String> argsMap = new HashMap<>();
        if (idArgs.length <= 1) {
            throw new SamMissingTaskArgException();
        }
        argsMap = Parser.parseTaskArgs(argsMap, idArgs[1]);
        Task task = tasks.updateTask(id, argsMap);
        if (task == null) {
            throw new SamInvalidTaskException();
        }

        storage.save(tasks);

        result.addMessage(Dialog.EDIT.getDialog());
        result.addTask(task);
        return result;
    }
}
