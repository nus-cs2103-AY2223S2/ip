package duke.parser;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TagCommand;
import duke.command.UnmarkCommand;
import duke.commandtype.CommandType;
import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Class that helps with interpreting user input
 */
public class Parser {
    /**
     * method Determines what command to run
     * @param inputLine a String input by the user
     * @return a Command depending on what the user has input
     * @throws DukeException if the input is not recognised
     */
    public static Command parse(String inputLine) throws DukeException {
        String[] words = inputLine.split(" ");

        try {
            CommandType commandtype = CommandType.valueOf(words[0].toUpperCase());
            switch (commandtype) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                int taskNoMark = Integer.parseInt(words[1]);
                return new MarkCommand(taskNoMark);
            case UNMARK:
                int taskNoUnmark = Integer.parseInt(words[1]);
                return new UnmarkCommand(taskNoUnmark);
            case DEADLINE:
                String[] deadlineParts = inputLine.split("/");
                Deadline task = new Deadline(deadlineParts[0].split(" ", 2)[1], 0, deadlineParts[1]);
                return new AddCommand(task);
            case EVENT:
                String[] eventParts = inputLine.split(" /");
                Event event = new Event(eventParts[0].split(" ", 2)[1], 0, eventParts[1], eventParts[2]);
                return new AddCommand(event);
            case TODO:
                Todo todo = new Todo(inputLine.split(" ", 2)[1], 0);
                return new AddCommand(todo);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(words[1]));
            case FIND:
                return new FindCommand(inputLine.split(" ", 2)[1]);
            case TAG:
                String[] tagParts = inputLine.split(" ", 3);
                return new TagCommand(Integer.parseInt(words[1]), tagParts[2]);
            default:
                throw new DukeException("Not a valid command: " + inputLine);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Not a valid command: " + inputLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException.checkInput(inputLine);
            throw new DukeException("Incorrect command format: " + inputLine);
        }
    }

    /**
     * Interprets user input time
     * @param input a String representing time in the format dd-MM-yyyy HHmm
     * @return a LocalDateTime to be stored in the Task object
     */
    public static LocalDateTime formatDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    /**
     * Reverses the process of the previous method for easy storage of the tasks
     * @param input a LocalDateTime stored by the Task object
     * @return a String representing the original user input time
     */
    public static String reverseFormatDateTime(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return input.format(formatter);
    }

    /**
     * Transforms the saved LocalDateTime time to one that is more readable
     * @param dateTime LocalDateTime saved by Task object
     * @return a readable time as a String
     */
    public static String transformDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' HH:mm");
        return dateTime.format(outputFormatter);
    }
}
