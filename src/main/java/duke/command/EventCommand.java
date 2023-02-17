package duke.command;

import duke.exceptions.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to create events.
 */
public class EventCommand {
    /**
     * Creates an event that is added to a list of existing tasks.
     * @param tasks the list of tasks.
     * @param input user's input describing the event.
     * @return acknowledgement of the event being successfully added.
     * @throws DukeException if invalid description or dates were found.
     */
    public static String addEvent(TaskList tasks, String input) throws DukeException {
        if (input.length() < 7) {
            throw new DukeException("The description of a <event> cannot be empty!");
        }
        String info = input.substring(6);
        if (!info.matches(".+ /from .+ /to .+")) {
            throw new DukeException("<event> is to be used as such: "
                    + "$ event <des> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
        }
        String[] sp = info.split(" /from ");
        if (sp[1].trim().equals("")) {
            throw new DukeException("<event> is missing start-time <YYYY-MM-DD>");
        }
        String[] time = sp[1].split(" /to ");
        if (time[1].trim().equals("")) {
            throw new DukeException("<event> is missing end-time <YYYY-MM-DD>");
        }
        Task task = new Event(sp[0].trim(), time[0].trim(), time[1].trim());
        tasks.addTask(task);
        return "Got it. I've added this task:\n\t"
                + task.getStatusIcon()
                + "\n" + "Now you have "
                + tasks.getSize()
                + " task(s) in the list.";
    }
}
