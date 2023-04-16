package logic.commands;

import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.DukeException;

/**
 * Class representing the Deadline Command
 */
public class DeadlineCommand extends Command {
    static final String DEADLINE_DATETIME_FORMAT = "dd-MM-yyyy HHmm";
    static final String DEADLINE_STORAGE_FORMAT = "MMM dd yyyy, HHmm";
    private String[] command;

    /**
     * Constructor for DeadlineCommand
     * @param command The command to be executed
     */
    public DeadlineCommand(String[] command) {
        this.command = command;
    }

    /**
     * Validates the Deadline Command
     * @param command The command to be validated
     * @throws DukeException
     */
    public static void validate(String[] command) throws DukeException {
        assert command.length > 0 : "Command should not be empty";

        if (command.length == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String unparsedDatetime = Parser.splitArray(command, "/by").get(1);

        if (!Parser.isValidDatetime(unparsedDatetime, DEADLINE_DATETIME_FORMAT)) {
            throw new DukeException(DEADLINE_DATETIME_FORMAT);
        }
    }

    /**
     * Parses the Deadline Command
     * @param unparsedDatetime The unparsed datetime
     * @return The parsed datetime
     */
    public static LocalDateTime parseDeadlineDatetime(String unparsedDatetime) {
        return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT));
    }

    /**
     * Parses the Deadline Command
     * @param storageDatetime The unparsed datetime
     * @return The parsed datetime
     */
    public static LocalDateTime parseDeadlineStorage(String storageDatetime) {
        return LocalDateTime.parse(storageDatetime, DateTimeFormatter.ofPattern(DEADLINE_STORAGE_FORMAT));
    }

    /**
     * Executes the Deadline Command
     * @param taskList The TaskList to be modified
     * @return The response to the Deadline Command
     */
    @Override
    public String execute(TaskList taskList) {
        String description = Parser.splitArray(this.command, "/by").get(0);
        String unparsedDatetime = Parser.splitArray(this.command, "/by").get(1);
        assert !unparsedDatetime.equals("") : "Parsing error occured in Deadline: datetime";

        LocalDateTime by = parseDeadlineDatetime(unparsedDatetime);
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        return Response.getAddTaskResponse(newDeadline, taskList);
    }
}
