package duke.commands.indexedCommand;

import java.util.List;

import duke.Duke;
import duke.commands.Command;
import duke.parser.Arguments;
import duke.task.Task;

/**
 * Indexed Commands only take in 1 index parameter. The command
 * will then execute using the task with that index in the Duke
 * instance's task list
 */
public abstract class IndexedCommand extends Command {
    public IndexedCommand(String label) {
        super(label);
    }
    
    @Override
    protected final void executeInternal(Arguments argObj, final Duke instance) throws ValidationException {
        List<Task> tasks = instance.getTaskList();
        String[] args = argObj.getOriginalArgs();
        try {
            validate(args.length > 1, String.format("Needed an index for %s", getLabel()));

            int index = Integer.parseInt(args[1]);
            if (index < 1 || index > tasks.size()) {
                throw new ValidationException("Invalid index!\n");
            }

            Task task = tasks.get(index - 1);
            runWithTask(task, instance);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid index!\n");
        }
    }

    protected abstract void runWithTask(Task task, Duke instance);
}
