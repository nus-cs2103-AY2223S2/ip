package windycall.Handler;

import windycall.parser.Parser;
import windycall.task.Event;
import windycall.task.Task;
import windycall.exception.WindyCallException;

import java.time.LocalDate;

public class AddEventHandler extends AddTaskHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static Task handleAddEvent(String message) throws WindyCallException {
        int idxFrom = message.indexOf("/from");
        int idxTo = message.indexOf("/to");
        if (message.length() == 5 || message.substring(5).trim().isEmpty()
                || (idxFrom != -1 && message.substring(5, idxFrom).trim().isEmpty())) {
            throw new WindyCallException("OOPS!!! The description of an event cannot be empty!");
        }
        if (idxFrom == -1) {
            throw new WindyCallException("OOPS!!! You haven't specify start time of the event!");
        }
        if (message.length() == idxFrom + 5 || message.substring(idxFrom + 5).trim().isEmpty()
                || (idxTo != -1 && message.substring(idxFrom, idxTo).trim().isEmpty())) {
            throw new WindyCallException("OOPS!!! You haven't specify start time of the event!");
        }
        if (idxTo == -1) {
            throw new WindyCallException("OOPS!!! You haven't specify end time of the event!");
        }
        if (message.length() == idxTo + 3 || message.substring(idxTo + 3).trim().isEmpty()) {
            throw new WindyCallException("OOPS!!! You haven't specify end time of the event!");
        }
        String description = message.substring(6, idxFrom - 1);
        String fromStr = message.substring(idxFrom + 6, idxTo - 1);
        String toStr = message.substring(idxTo + 4);
        LocalDate from = Parser.processDate(fromStr);
        LocalDate to = Parser.processDate(toStr);
        return new Event(description, false, from, to);
    }
}
