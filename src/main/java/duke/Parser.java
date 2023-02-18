package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
		SHOWSAVE,
		BYE,
	}

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
				String[] splitted = inputs[1].split("/by ", 2);
				String[] byWhen = splitted[1].split(" ", 2);
				LocalDate d1 = LocalDate.parse(byWhen[0]);
				LocalTime t1 = LocalTime.parse(byWhen[1]);
				Task newDeadline = new Task.Deadline(splitted[0], d1, t1);
				return new AddCommand(newDeadline);
			case EVENT:
				String[] fromWhenToWhen = inputs[1].split(" ", 5);
				String fromWhen = fromWhenToWhen[2];
				String toWhen = fromWhenToWhen[4];
				Task newEvent = new Task.Event(inputs[1], fromWhen, toWhen);
				return new AddCommand(newEvent);
			case SAVE:
				String[] noAndDesc = inputs[1].split(" ", 2);
				Save newSave = new Save(Integer.parseInt(noAndDesc[0]), noAndDesc[1]);
				SaveList saveData = Storage.readSaveData();
				saveData.addSave(Integer.parseInt(noAndDesc[0]), newSave);
				Storage.writeSaveData(saveData, Integer.parseInt(noAndDesc[0]));
				return new SaveCommand(Integer.parseInt(noAndDesc[0]), noAndDesc[1]);
			case LOAD:
				int loadNo = Integer.parseInt(inputs[1]);
				assert (loadNo > 0 && loadNo <= 3) : "Save slot must be between 1 and 3!";
				return new LoadCommand(loadNo);
			case SHOWSAVE:
				SaveList savedData = Storage.readSaveData();
				return new ListSaveCommand(savedData);
			default:
				throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		} catch (Exception ex) {
			throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
	}
}
