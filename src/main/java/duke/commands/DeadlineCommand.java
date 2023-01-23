package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index_by = input.indexOf("/");
            if (index_by - 1 < 9) {
                throw new DukeException("    OOPS!!! The description of a deadline cannot be empty.");
            }
            if (index_by + 4 > input.length()) {
                throw new DukeException("    OOPS!!! You are missing the deadline of a deadline.");
            }
            if (!input.substring(index_by, index_by + 4).equals("/by ")) {
                throw new DukeException("    OOPS!!! Deadline should be followed by a /by command.");
            }
            Deadline d = new Deadline(input.substring(9, index_by - 1),
                    input.substring(index_by + 4, input.length()));

            tasks.add(d);
            storage.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + d);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            return true;
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return false;
        } catch (DateTimeParseException new_e) {
            System.out.println("    Deadline must have a date of the following format:\n    1. yyyy-MM-dd\n    2. yyyy-MM-dd HHmm");
            return false;
        }
    }
}
