package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    private String input;

    public ListCommand(String input) {
        this.input = input;
    }
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.print("    " + num + ". " + tasks.get(i) + "\n");
        }
        return true;
    }
}
