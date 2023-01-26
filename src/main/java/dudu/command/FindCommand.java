package dudu.command;

import dudu.exception.DuduException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

public class FindCommand extends Command {
    private String input;
    public FindCommand(Instruction instruction, String input) {
        super(instruction, input);
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        int index = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: list.getList()) {
            if (task.getDescription().contains(input)) {
                index += 1;
                System.out.print(index + ".");
                System.out.println(task);
            }
        }
    }
}
