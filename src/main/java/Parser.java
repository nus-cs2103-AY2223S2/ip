import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {

    public static Command parse(String command) throws DukeException {
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.startsWith("mark")) {
                return new MarkCommand(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark")) {
                return new UnmarkCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("delete")) {
                return new DeleteCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("todo")) {
                return new TodoCommand(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] str1 = command.substring(9).split("/");
                return new DeadlineCommand(str1[0], convertStringToDate(str1[1].substring(3, 13)));
            } else if (command.startsWith("event")) {
                String[] str2 = command.substring(6).split("/");
                return new EventCommand(str2[0], convertStringToDate(str2[1].substring(5, 15)), convertStringToDate(str2[2].substring(3, 13)));
            } else {
                throw new DukeException("Please input a valid command");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input the description");
        }

    }

    public static LocalDate convertStringToDate(String date) throws DukeException {
        try {
            String[] arr = date.split("-");
            return LocalDate.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
        }
    }

}
