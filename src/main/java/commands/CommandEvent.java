package commands;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;
import tasks.Event;
import tasks.Task;

/**
 * Handles 'event' command.
 */
public class CommandEvent extends Command {
    /**
     * Adds an Event task to the taskList and returns a String form of the action.
     * @param userInput The user's String input in array form.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            String eventSentence = userInput[1];
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
            assert (!eventName.equals("") && !fromDate.equals("") && !toDate.equals(""));
            Task eventToAdd = new Event(eventName, fromDate, toDate);
            taskList.add(eventToAdd);
            autoSave(taskList);
            return ("Task added:\n " + eventToAdd + "\n" + "There are now " + taskList.size()
                    + " task(s) in your list.");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatCommandError("event",
                    "event <insert description> /from <insert from field> "
                            + "/to <insert to field>"));
        }
    }
}
