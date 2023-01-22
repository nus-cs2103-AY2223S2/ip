import Tasks.Event;
import Tasks.Task;

import java.io.IOException;

public class CommandEvent extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String eventSentence = Duke.userScan.nextLine();
            String eventName = eventSentence.substring(0, eventSentence.indexOf(" /from"));
            // ERROR: event description is blank.
            if (eventName.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("event description cannot be blank."));
            }
            String fromDate = eventSentence.substring(eventSentence.indexOf(" /from")+7,
                    eventSentence.indexOf(" /to"));
            // ERROR: event's from field is blank.
            if (fromDate.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("event's from field cannot be blank."));
            }
            String toDate = eventSentence.substring(eventSentence.indexOf(" /to")+5);
            // ERROR: event's to field is blank.
            if (toDate.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("event's to field cannot be blank."));
            }
            Task eventToAdd = new Event(eventName, fromDate, toDate);
            Duke.taskList.add(eventToAdd);
            Duke.ui.print("Task added:\n " + eventToAdd + "\n" + "There are now " + Duke.taskList.size() +
                    " task(s) in your list.");
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
        // ERROR: event format is anything other than [ event /from <insert from field> /to <insert to field> ]
        catch (StringIndexOutOfBoundsException err) {
            throw new DukeException(Duke.ui.formatCommandError("event",
                    "event /from <insert from field> " +
                            "/to <insert to field>"));
        }
    }
}
