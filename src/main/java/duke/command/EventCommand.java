package duke.command;

import duke.task.Event;
import duke.task.Task;

/**
 * Represents an add event task command.
 */
public class EventCommand extends AddCommand {
    @Override
    protected Task createTask(String input) {
        input = input.replace("event ", "");
        String[] args = input.split(" /from ");

        String description = args[0];

        String[] startAndEnd = args[1].split(" /to ");
        String start = startAndEnd[0];
        String end = startAndEnd[1];

        return new Event(false, description, start, end);
    }
}
