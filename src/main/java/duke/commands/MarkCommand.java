package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new DukeException("    OOPS!!! You are missing the number of the task to be marked.");
            }
            int index = Integer.parseInt(input.substring(5));
            Task task = tasks.get(index - 1);
            task.mark();
            storage.saveTaskList(tasks);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task);
            return true;
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return false;
        } catch (NumberFormatException nfe) {
            System.out.println("    OOPS!!! Mark has to be followed by an int.");
            return false;
        } catch (IndexOutOfBoundsException i) {
            System.out.println("    OOPS!!! There are insufficient tasks.");
            return false;
        }
    }
}
