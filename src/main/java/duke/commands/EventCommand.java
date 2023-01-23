package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.tasks.Event;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index_from = input.indexOf("/");
            int index_to = input.lastIndexOf("/");

            if ((index_from + 6 > index_to - 1) || (index_to + 4 > input.length()) || (index_from - 1 < 6)) {
                throw new DukeException("    OOPS!!! Event must be in the format\n" +
                        "    event <description> /from <date> /to <date>");
            }
            if (!(input.substring(index_from, index_from + 6).equals("/from ") &&
                    input.substring(index_to, index_to + 4).equals("/to "))) {
                throw new DukeException("    OOPS!!! Deadline should be followed by a /from and a /to command.");
            }
            Event e = new Event(input.substring(6, index_from - 1),
                    input.substring(index_from + 6, index_to - 1),
                    input.substring(index_to + 4, input.length()));
            tasks.add(e);
            storage.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + e);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
}
