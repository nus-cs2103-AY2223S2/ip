package sam.command;

import java.util.HashMap;
import java.util.Map;

import sam.Dialog;
import sam.Ui;
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

/**
 * Represents a user command to clone a task.
 */
public class CloneCommand extends Command {
    /**
     * Constructs a new CloneCommand.
     *
     * @param args The command string.
     */
    public CloneCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
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
        if (idArgs.length > 1) {
            argsMap = Parser.parseTaskArgs(argsMap, idArgs[1]);
        }
        Task task = tasks.cloneTask(id, argsMap);
        if (task == null) {
            throw new SamInvalidTaskException();
        }
        ui.respond(Dialog.CLONE.getDialog(),
            task.toString());
        storage.save(tasks);
    }
}
