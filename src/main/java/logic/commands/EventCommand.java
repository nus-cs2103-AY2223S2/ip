package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.Event;

import exceptions.DukeException;


public class EventCommand extends Command{
	static final String EVENT_DATETIME_FORMAT = "dd-MM-yyyy HHmm";
	static final String EVENT_STORAGE_FORMAT = "MMM dd yyyy, HHmm";
	static final String EVENT_INPUT_FORMAT = "dd-MM-yyyy HHmm - dd-MM-yyyy HHmm";
	private String[] command;

	public EventCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

	}

	public static LocalDateTime parseEventDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT));
	}

	public static LocalDateTime parseEventStorage(String storageDatetime) {
		return LocalDateTime.parse(storageDatetime, DateTimeFormatter.ofPattern(EVENT_STORAGE_FORMAT));
	}

	@Override
	public String execute(TaskList taskList) {

		for (int i = 0; i < command.length; i++) {
			System.out.println(command[i]);
		}
		
		String description = Parser.splitArray(this.command, "/from").get(0);
		
		String unparsedDatetime = Parser.splitArray(this.command, "/from").get(1);
		System.out.println(unparsedDatetime);


		String fromTime = unparsedDatetime.split("/to", 2)[0].trim();
		String toTime = unparsedDatetime.split("/to", 2)[1].trim();

		LocalDateTime from = parseEventDatetime(fromTime);
		LocalDateTime to = parseEventDatetime(toTime);

		Event newEvent = new Event(description, from, to);
		taskList.add(newEvent);
		return Response.getAddTaskResponse(newEvent, taskList);
	}
}
