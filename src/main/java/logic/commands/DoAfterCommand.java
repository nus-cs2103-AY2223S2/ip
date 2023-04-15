package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import exceptions.DukeException;
import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.DoAfter;

public class DoAfterCommand extends Command {
	static final String DO_AFTER_DATETIME_FORMAT = "dd-mm-yyyy HHmm";
	static final String DO_AFTER_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
	private String[] command;

	public DoAfterCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

		if (command.length == 0) {
			throw new DukeException("The description of a doafter cannot be empty.");
		}
	}

	public static LocalDateTime parseDoAfterDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DO_AFTER_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
	}

	@Override
	public String execute(TaskList taskList) {
		String description = Parser.splitArray(this.command, "/after").get(0);

		String unparsedDatetime = Parser.splitArray(this.command, "/after").get(1);

		LocalDateTime after = parseDoAfterDatetime(unparsedDatetime);
		DoAfter newDoAfter = new DoAfter(description, after);
		taskList.add(newDoAfter);
		return Response.getAddTaskResponse(newDoAfter, taskList);
	}
}
