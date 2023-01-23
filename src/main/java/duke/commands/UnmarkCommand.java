package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 7) {
                throw new DukeException("    OOPS!!! You are missing the number of the task to be unmarked.");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index - 1);
            task.unmark();
            storage.saveTaskList(tasks);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task);
            return true;
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return false;
        } catch (NumberFormatException nfe) {
            System.out.println("    OOPS!!! Unmark has to be followed by an int.");
            return false;
        } catch (IndexOutOfBoundsException i) {
            System.out.println("    OOPS!!! There are insufficient tasks.");
            return false;
        }
    }
}
