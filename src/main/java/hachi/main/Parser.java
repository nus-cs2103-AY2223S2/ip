package hachi.main;

import hachi.commands.*;
import hachi.tasks.Deadline;
import hachi.tasks.Event;
import hachi.tasks.Task;
import hachi.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles user instruction and perform the corresponding type of command.
 */
public class Parser {
    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     *
     * @param input The user's input string.
     * @return A specific type of command.
     * @throws IllegalArgumentException if command is unknown.
     */
    public static Command parse(String input) throws IllegalArgumentException {
        String[] words = input.split(" ");
        Type t = Type.valueOf(words[0].toUpperCase());
        switch (t) {
        case LIST:
            return new ListCommand(input);

        case DEADLINE:
            return new DeadlineCommand(input);

        case UNMARK:
            return new UnmarkCommand(input);

        case TODO:
            return new TodoCommand(input);

        case EVENT:
            return new EventCommand(input);

        case DELETE:
            return new DeleteCommand(input);

        case MARK:
            return new MarkCommand(input);

        case BYE:
            return new ExitCommand(input);

        case FIND:
            return new FindCommand(input);

        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Converts strings of tasks in the hard disk to Task instances.
     * @param input String of task to be converted to a Task instance.
     * @return A Task instance.
     */
    public static Task parseSaved(String input) {
        String[] splitInput = input.split(" ");
        String taskType = splitInput[0];
        String taskStatus = splitInput[2];
        String taskDescription = splitInput[4] ;
        Task newTask;

        switch (taskType) {
            case "T":
                newTask = new Todo(taskDescription);
                break;
            case "D":
                String deadlineTiming = splitInput[6];
                newTask = new Deadline(taskDescription, LocalDate.parse(deadlineTiming, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                break;
            case "E":
                newTask = new Event(taskDescription, splitInput[6], splitInput[8]);
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (taskStatus.equals("1")) {
            newTask.mark();
        } else {
            newTask.unmark();
        }
        return newTask;
    }
}




