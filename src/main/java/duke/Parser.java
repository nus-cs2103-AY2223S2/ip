package duke;

import duke.command.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Parser class encapsulates the
 * parsing of user inputs into commands
 * to be executed by Duke.
 */
public class Parser {

	enum Type {
		TODO,
		DEADLINE,
		EVENT,
		FIND,
		MARK,
		UNMARK,
		LIST,
		DELETE,
		SAVE,
		LOAD,
		SHOWSAVES,
		BYE,
	}

	/**
	 * Parses the user input into a command
	 *
	 * @param msg The user input
	 *
	 * @return The resulting command.
	 */
	public static Command parseInput(String msg) throws Exception {
		try {
			String [] inputs = msg.split(" ", 2);
			Type cmdType = Type.valueOf(inputs[0].toUpperCase());
			switch (cmdType) {
			case BYE:
				return new ExitCommand();
			case LIST:
				return new ListCommand();
			case MARK:
				return new MarkCommand(Integer.parseInt(inputs[1]), true);
			case UNMARK:
				return new MarkCommand(Integer.parseInt(inputs[1]), false);
			case DELETE:
				return new DeleteCommand(Integer.parseInt(inputs[1]));
			case FIND:
				return new FindCommand(inputs[1]);
			case TODO:
				Task newTodo = new Task.Todo(inputs[1]);
				return new AddCommand(newTodo);
			case DEADLINE:
				Task newDeadline = parseDeadline(inputs[1]);
				return new AddCommand(newDeadline);
			case EVENT:
				Task newEvent = parseEvent(inputs[1]);
				return new AddCommand(newEvent);
			case SAVE:
				String[] noAndDesc = inputs[1].split(" ", 2);
				parseSave(noAndDesc);
				return new SaveCommand(Integer.parseInt(noAndDesc[0]), noAndDesc[1]);
			case LOAD:
				int loadNo = Integer.parseInt(inputs[1]);
				assert (loadNo > 0 && loadNo <= 3) : "Save slot must be between 1 and 3!";
				return new LoadCommand(loadNo);
			case SHOWSAVES:
				SaveList savedData = Storage.readSaveData();
				return new ShowSavesCommand(savedData);
			default:
				throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		} catch (Exception ex) {
			throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
	}

	private static Task parseDeadline(String input) {
		String[] splitted = input.split("/by ", 2);
		String[] byWhen = splitted[1].split(" ", 2);
		LocalDate d1 = LocalDate.parse(byWhen[0]);
		LocalTime t1 = LocalTime.parse(byWhen[1]);

		return new Task.Deadline(splitted[0], d1, t1);
	}

	private static Task parseEvent(String input) {
		String[] fromWhenToWhen = input.split(" ", 5);
		String fromWhen = fromWhenToWhen[2];
		String toWhen = fromWhenToWhen[4];
		return new Task.Event(fromWhenToWhen[0], fromWhen, toWhen);
	}

	private static void parseSave(String[] input) throws IOException {
		Save newSave = new Save(Integer.parseInt(input[0]), input[1]);
		SaveList saveData = Storage.readSaveData();
		saveData.addSave(Integer.parseInt(input[0]), newSave);
		Storage.writeSaveData(saveData, Integer.parseInt(input[0]));
	}
}
