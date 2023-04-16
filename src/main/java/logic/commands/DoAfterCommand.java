package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.DukeException;
import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.DoAfter;

public class DoAfterCommand extends Command {
	static final String DO_AFTER_DATETIME_FORMAT = "dd-MM-yyyy HHmm";
	static final String DO_AFTER_STORAGE_FORMAT = "MMM dd yyyy, HHmm";
	private String[] command;

	public DoAfterCommand(String[] command) {
		this.command = command;
	}

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

	public static LocalDateTime parseDoAfterDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DO_AFTER_DATETIME_FORMAT));
	}

	public static LocalDateTime parseDoAfterStorage(String storageDatetime) {
		return LocalDateTime.parse(storageDatetime, DateTimeFormatter.ofPattern(DO_AFTER_STORAGE_FORMAT));
	}

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
