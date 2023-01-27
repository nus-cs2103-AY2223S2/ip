package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    private String input;

    /**
     * FindCommand constructor.
     *
     * @param input The user's input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = this.input.substring(5, input.length());
            int counter = 0;
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.contains(keyword)) {
                    counter++;
                    System.out.print("    " + counter + ". " + t + "\n");
                }
            }
            return true;

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
