package logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

		if (command.length == 0) {
			throw new DukeException("The description of a event cannot be empty.");
		}

		String dateString = Parser.splitArray(command, "/from").get(1);


		if (!Parser.isValidDatetime(dateString, EVENT_DATETIME_FORMAT)) {
			throw new DukeException(EVENT_DATETIME_FORMAT);
		}
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
		
		System.out.println(description);


		String temp = "Sep 12 2019, 1800";
		LocalDateTime from = parseEventDatetime(temp);

		Event newEvent = new Event(description, from, from);
		taskList.add(newEvent);
		return Response.getAddTaskResponse(newEvent, taskList);
	}
}
