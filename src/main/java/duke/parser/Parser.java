package duke.parser;
import duke.command.*;
import duke.commandtype.CommandType;
import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String inputLine) throws DukeException {
        try {
            /**
             * @param inputLine: a String that is the command entered by the user
             * @param words[]: an array whose elements are from inputline separated by
             * a space. used to determine which command is entered
             */
            DukeException.checkInput(inputLine);
            String words[] = inputLine.split(" ");
            CommandType commandtype = CommandType.valueOf(words[0].toUpperCase());
            switch (commandtype) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case MARK:
                /**
                 * change the specified task's status to "[X]"
                 */
                    int taskNoMark = Integer.parseInt(words[1]);
                    return new MarkCommand(taskNoMark);
                case UNMARK:
                /**
                 * changed the specified task's staus to "[ ]"
                 */
                    int taskNoUnmark = Integer.parseInt(words[1]);
                    return new UnmarkCommand(taskNoUnmark);
                case DEADLINE:
                /**
                 * creates and adds a deadline task to the arraylist of all tasks
                 */
                    String[] parts = inputLine.split("/");
                    Deadline task = new Deadline(parts[0].split(" ", 2)[1], 0, parts[1]);
                    return new AddCommand(task);
                case EVENT:
                /**
                 * creates and adds an event task to the arraylist of all tasks
                 */
                    String[] parts1 = inputLine.split(" /");
                    Event event = new Event(parts1[0].split(" ", 2)[1], 0, parts1[1], parts1[2]);
                    return new AddCommand(event);
                case TODO:
                /**
                 * creates and adds a todo task to the arraylist of all tasks
                 */
                    Todo todo = new Todo(inputLine.split(" ", 2)[1], 0);
                    return new AddCommand(todo);
                case DELETE:
                /**
                 * removes the task at the specified index
                 */
                    return new DeleteCommand(Integer.parseInt(words[1]));
                default:
                    throw new DukeException("Not a valid command: " + inputLine);
                }
        } catch (DukeException e) {
            /**
             * prints out the error message if an error is caught
             */
            throw e;
        }
    }

    public static LocalDateTime formatDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    public static String reverseFormatDateTime(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return input.format(formatter);
    }

    public static String TransformDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' HH:mm");
        return dateTime.format(outputFormatter);
    }
}
