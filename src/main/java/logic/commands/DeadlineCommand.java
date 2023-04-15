package logic.commands;

import logic.commands.Command;
import logic.parser.Parser;
import model.TaskList;
import model.tasks.Task;
import model.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import exceptions.DukeException;

public class DeadlineCommand extends Command{
	static final String DEADLINE_DATETIME_FORMAT = "dd-mm-yyyy HHmm";
    static final String DEADLINE_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
	private String[] command;

	public DeadlineCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

		if (command.length == 0) {
			throw new DukeException("The description of a deadline cannot be empty.");
		}
	}

	public static LocalDateTime parseDeadlineDatetime(String unparsedDatetime) {
		return LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
	}


	@Override
	public String execute(TaskList taskList) {
		String description = Parser.splitArray(this.command, "/by").get(0);

        String unparsedDatetime = Parser.splitArray(this.command, "/by").get(1);

        LocalDateTime by = parseDeadlineDatetime(unparsedDatetime);
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        return "Got it. I've added this task: " + newDeadline + "\nNow you have " +
				taskList.size() + " tasks in the list.";
	}
}
