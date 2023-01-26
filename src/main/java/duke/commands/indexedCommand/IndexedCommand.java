package duke.commands.indexedCommand;

import java.util.List;

import duke.Duke;
import duke.commands.Command;
import duke.task.Task;

public abstract class IndexedCommand extends Command {
    public IndexedCommand(String label) {
        super(label);
    }
    
    @Override
    protected void execute(String[] args, final Duke instance) throws ValidationException {
        List<Task> tasks = instance.getTaskList();
        try {
            validate(args.length > 1, String.format("Needed an index for %s", getLabel()));

            int index = Integer.parseInt(args[1]);
            if (index < 1 || index > tasks.size()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid index!\n");
        }
    }

    protected abstract void runWithTask(Task task, Duke instance);
}
