package duke.commands;
import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (5 > input.length()) {
                throw new DukeException("    OOPS!!! The description of a todo cannot be empty.");
            }
            Todo td = new Todo(input.substring(5, input.length()));
            tasks.add(td);
            storage.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + td);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
}
