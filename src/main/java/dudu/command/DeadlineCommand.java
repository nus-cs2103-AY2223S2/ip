package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;
import dudu.task.Deadline;
import dudu.task.TaskList;
import dudu.util.Storage;

public class DeadlineCommand extends Command {
    private String input;
    public DeadlineCommand(Instruction instruction, String input) {
        super(instruction, input);
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        if (!input.contains(" /by ")) {
            throw new EmptyDescriptionException("deadline", "date", "Missing end date");
        }
        String[] inputStr = input.split(" /by ");
        storage.saveTask(list.addTask(new Deadline(inputStr[0], inputStr[1])));
    }
}
