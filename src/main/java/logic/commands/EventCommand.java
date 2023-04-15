package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.Event;

import exceptions.DukeException;


public class EventCommand extends Command{
	static final String EVENT_DATETIME_FORMAT = "dd-mm-yyyy HHmm";
	static final String EVENT_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
	private String[] command;

	public EventCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

		if (command.length == 0) {
			throw new DukeException("The description of a event cannot be empty.");
		}
	}

	public static LocalDateTime parseEventDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
	}

	@Override
	public String execute(TaskList taskList) {
		String description = Parser.splitArray(this.command, "/from").get(0);
		String eventFrom = Parser.splitArray(this.command, "/from").get(1);
		String eventTo = Parser.splitArray(this.command, "/to").get(1);


		LocalDateTime from = parseEventDatetime(eventFrom);
		LocalDateTime to = parseEventDatetime(eventTo);

		Event newEvent = new Event(description, from, to);
		taskList.add(newEvent);
		return Response.getAddTaskResponse(newEvent, taskList);
	}
}
