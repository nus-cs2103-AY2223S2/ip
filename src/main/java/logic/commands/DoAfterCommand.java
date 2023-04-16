package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.DukeException;
import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.DoAfter;

/**
 * Class representing the DoAfter Command
 */
public class DoAfterCommand extends Command {
	static final String DO_AFTER_DATETIME_FORMAT = "dd-MM-yyyy HHmm";
	static final String DO_AFTER_STORAGE_FORMAT = "MMM dd yyyy, HHmm";
	private String[] command;

	/**
	 * Constructor for DoAfterCommand
	 * @param command The command to be executed
	 */
	public DoAfterCommand(String[] command) {
		this.command = command;
	}

	/**
	 * Validates the DoAfter Command
	 * @param command The command to be validated
	 * @throws DukeException
	 */
	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

		if (command.length == 0) {
			throw new DukeException("The description of a doafter cannot be empty.");
		}

		String unparsedDatetime = Parser.splitArray(command, "/after").get(1);

		if (!Parser.isValidDatetime(unparsedDatetime, DO_AFTER_DATETIME_FORMAT)) {
			throw new DukeException(DO_AFTER_DATETIME_FORMAT);
		}
	}

	/**
	 * Parses the DoAfter Command
	 * @param unparsedDatetime The unparsed datetime
	 * @return The parsed datetime
	 */
	public static LocalDateTime parseDoAfterDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DO_AFTER_DATETIME_FORMAT));
	}

	/**
	 * Parses the DoAfter Command
	 * @param storageDatetime The unparsed datetime
	 * @return The parsed datetime
	 */
	public static LocalDateTime parseDoAfterStorage(String storageDatetime) {
		return LocalDateTime.parse(storageDatetime, DateTimeFormatter.ofPattern(DO_AFTER_STORAGE_FORMAT));
	}

	/**
	 * Executes the DoAfter Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the user
	 */
	@Override
	public String execute(TaskList taskList) {
		String description = Parser.splitArray(this.command, "/after").get(0);
		String unparsedDatetime = Parser.splitArray(this.command, "/after").get(1);
		assert !unparsedDatetime.equals("") : "Parsing error occured in DoAfter: datetime";

		LocalDateTime after = parseDoAfterDatetime(unparsedDatetime);
		DoAfter newDoAfter = new DoAfter(description, after);
		taskList.add(newDoAfter);
		return Response.getAddTaskResponse(newDoAfter, taskList);
	}
}
