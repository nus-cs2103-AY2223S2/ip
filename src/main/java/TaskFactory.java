import java.time.DateTimeException;
import java.time.LocalDateTime;

public class TaskFactory {

    private static void validateName(String input) throws InvalidTaskException {
        if (input.trim().length() < 1) {
            throw new InvalidTaskException();
        }
    }

    private static String[] getNameDeadlinePair(String input) throws InvalidTaskException {
        int byIndex = input.indexOf("/by");
        if (byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] pair = new String[2];
        pair[0] = input.substring(0, byIndex - 1);
        validateName(pair[0]);
        pair[1] = input.substring(byIndex + 4);
        return pair;
    }

    private static String[] getNameStartEndTuple(String input) throws InvalidTaskException {
        int fromIndex = input.indexOf("/from");
        int byIndex = input.indexOf("/to");
        if (fromIndex < 1 || byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] tuple = new String[3];
        tuple[0] = input.substring(0, fromIndex - 1);
        validateName(tuple[0]);
        tuple[1] = input.substring(fromIndex + 6, byIndex - 1);
        tuple[2] = input.substring(byIndex + 4);
        return tuple;
    }

    public static String formatTime(String time) {
        String hrStr = time.substring(0, 2);
        String minStr = time.substring(2, 4);
        return "T" + hrStr + ":" + minStr + ":00";
    }

    public static LocalDateTime parseDateTime(String input) {
        String[] dateTimePair = input.split(" ");
        String date = dateTimePair[0];
        String time = dateTimePair[1];
        try {
            time = formatTime(time);
            return LocalDateTime.parse(date + time);
        } catch (DateTimeException e) {
            Responses.printMessage(e.getMessage());
        }
        return null;
    }

    public static Task parseCommand(String command, String information) throws InvalidTaskException {
        if (command.equals("TODO")) {
            validateName(information);
            return new Todo(information);
        } else if (command.equals("DEADLINE")) {
            String[] pair = getNameDeadlinePair(information);
            return new Deadline(pair[0], parseDateTime(pair[1]));
        } else {
            String[] tuple = getNameStartEndTuple(information);
            return new Event(tuple[0], parseDateTime(tuple[1]), parseDateTime(tuple[2]));
        }
    }
}
