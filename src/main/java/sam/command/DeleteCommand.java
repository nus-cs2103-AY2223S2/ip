package sam.command;

import sam.Ui;
import sam.parser.Parser;
import sam.parser.SamInvalidIntException;
import sam.parser.SamInvalidTaskException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.SamMissingTaskException;
import sam.task.Task;
import sam.task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
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
        ui.talk("Ok, I'll remove the task from your list:",
                task.toString());
        storage.save(tasks);
    }
}
