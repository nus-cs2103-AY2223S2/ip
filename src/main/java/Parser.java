import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Parser {
    public static Optional<LocalDate> parseDate(String timeInput) {
        try {
            timeInput = timeInput.replaceAll("/","-");
            return Optional.of(LocalDate.parse(timeInput));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    public static int parseMarkUnmarkDelete(String lineInput) {
        return Integer.parseInt(lineInput.split(" ")[1]) - 1;
    }

    public static String[] parseDeadlineCmd(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String taskName;
        String dueDate;

        int indexOfBy = commandInput.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        taskName = commandInput.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            dueDate = commandInput.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

        String[] parseInfo = {taskName, dueDate};
        return parseInfo;
    }

    public static String[] parseEventCmd(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String taskName;
        String fromDate;
        String toDate;

        commandInput = commandInput.substring(6);
        int indexOfFrom = commandInput.indexOf("/from");
        int indexOfTo = commandInput.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new EventFromToNotSpecified("Event task requires keywords '/from' and '/to'");
        }

        taskName = commandInput.substring(0, indexOfFrom - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("The description of an event cannot be empty.");
        }

        fromDate = commandInput.substring(indexOfFrom + 6, indexOfTo - 1);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        try {
            toDate = commandInput.substring(indexOfTo + 4, commandInput.length());
           
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        String[] parseInfo = {taskName, fromDate, toDate};
        return parseInfo;
    }

}
