package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.DuplicateException;
import dudu.exception.EmptyDescriptionException;
import dudu.task.Deadline;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for set up deadline task.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructor for deadline task
     * @param input Name and date of the task.
     */
    public DeadlineCommand(String input) {
        super(input);
        this.input = input;
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        assert list != null;
        assert storage != null;
        if (!input.contains(" /by ")) {
            throw new EmptyDescriptionException("deadline", "date", "Missing end date");
        }
        String[] inputStr = input.split(" /by ");
        Deadline deadline = new Deadline(inputStr[0], inputStr[1]);
        boolean isDuplicate = list.getList().stream()
                .map(x -> x.getDescription())
                .anyMatch(task -> task.equals(deadline.getDescription()));
        if (isDuplicate) {
            throw new DuplicateException();
        }
        storage.saveTask(list.addTask(deadline));
        return "Got it. I've added this task:\n  " + deadline + "\n" + list.getTotalTask() + "\n";
    }
}
