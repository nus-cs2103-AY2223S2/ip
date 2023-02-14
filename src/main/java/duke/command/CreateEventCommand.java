package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.DukeException;
import duke.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that creates an event task.
 *
 * @author wz2k
 */
public class CreateEventCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating events.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateEventCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new event task and returns the reply
     * for event creation.
     *
     * @return Taskbot reply to the event creation.
     */
    @Override
    public String execute() {
        try {
            String startOfReply = "The following task has been added:\n";

            Task task = createEvent();
            taskList.addTask(task);
            storage.storeTask(task);

            return startOfReply + "  " + task;
        } catch (IOException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Creates an event.
     *
     * @return Event created.
     */
    public Event createEvent() {
        String[] commandMessageArr = commandMessage.split("/", 3);
        assert commandMessageArr.length == 3 : "event command should split into 3";

        return new Event(commandMessageArr[0].substring(6), false,
                commandMessageArr[1].substring(4).trim(),
                commandMessageArr[2].substring(2).trim());
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String args = commandMessage.substring(5).trim();
        System.out.println(args);
        if (args.length() == 0) {
            String emptyArgumentsMessage = "event arguments cannot be empty";
            throw new DukeException(emptyArgumentsMessage);
        }

        String[] argsArr = checkFromToFormat(args);

        if (argsArr[0].trim().length() == 0) {
            String emptyDescriptionMessage = "event description cannot be empty";
            throw new DukeException(emptyDescriptionMessage);
        }

        try {
            LocalDate.parse(argsArr[1].substring(5).trim());
            LocalDate.parse(argsArr[2].substring(3).trim());
        } catch (DateTimeParseException dtpe) {
            String wrongDateFormatMessage = "event dates should be of the format YYYY-MM-DD";
            throw new DukeException(wrongDateFormatMessage);
        }
    }

    /**
     * Checks if the message is of the correct event format.
     *
     * @param args Input arguments.
     * @return Tokenized arguments.
     * @throws DukeException If format is incorrect.
     */
    private String[] checkFromToFormat(String args) throws DukeException {
        String invalidFormatMessage = "event format is incorrect";
        String[] argsArrFront = args.split("/from", 2);
        if (argsArrFront.length != 2 || argsArrFront[0].length() == 0
                || argsArrFront[1].length() == 0) {
            throw new DukeException(invalidFormatMessage);
        }

        String[] argsArrBack = argsArrFront[1].split("/to", 2);
        if (argsArrBack.length != 2 || argsArrBack[0].length() == 0
                || argsArrBack[1].length() == 0) {
            throw new DukeException(invalidFormatMessage);
        }

        return args.split("/", 3);
    }
}
