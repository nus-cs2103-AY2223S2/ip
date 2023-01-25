package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;
import dudu.task.Deadline;
import dudu.task.Event;
import dudu.task.TaskList;
import dudu.util.Storage;

public class EventCommand extends Command {
    private String input;
    private String date;
    public EventCommand(Instruction instruction, String input) {
        super(instruction, input);
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        if (!input.contains(" /from ")) {
            throw new EmptyDescriptionException("event", "date" , "Missing date");
        }
        String[] inputStr = input.split(" /from ");
        String[] dateStr = inputStr[1].split(" /to ");
        storage.saveTask(list.addTask(new Event(inputStr[0], dateStr[0], dateStr[1])));
    }
}
