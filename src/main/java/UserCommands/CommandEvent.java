package UserCommands;

import java.util.Scanner;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;
import Tasks.Event;
import Tasks.Task;

/**
 * Handles 'event' command.
 */
public class CommandEvent extends Command {
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        try {
            String eventSentence = userScan.nextLine();
            String eventName = eventSentence.substring(0, eventSentence.indexOf(" /from"));
            // ERROR: event description is blank.
            if (eventName.strip().length() == 0) {
                throw new DukeException(ui.formatLogicError("event description cannot be blank."));
            }
            String fromDate = eventSentence.substring(eventSentence.indexOf(" /from") + 7,
                    eventSentence.indexOf(" /to"));
            // ERROR: event's from field is blank.
            if (fromDate.strip().length() == 0) {
                throw new DukeException(ui.formatLogicError("event's from field cannot be blank."));
            }
            String toDate = eventSentence.substring(eventSentence.indexOf(" /to") + 5);
            // ERROR: event's to field is blank.
            if (toDate.strip().length() == 0) {
                throw new DukeException(ui.formatLogicError("event's to field cannot be blank."));
            }
            Task eventToAdd = new Event(eventName, fromDate, toDate);
            taskList.add(eventToAdd);
            ui.print("Task added:\n " + eventToAdd + "\n" + "There are now " + taskList.size()
                    + " task(s) in your list.");
            return taskList;
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatCommandError("event",
                    "event /from <insert from field> "
                            + "/to <insert to field>"));
        }
    }
}
