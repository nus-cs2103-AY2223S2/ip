package iris;

import iris.command.AddTaskCommand;
import iris.command.Command;
import iris.command.DeleteTaskCommand;
import iris.command.ExitCommand;
import iris.command.FilterCommand;
import iris.command.FindCommand;
import iris.command.HelpCommand;
import iris.command.ListCommand;
import iris.command.MarkTaskCommand;
import iris.command.ResetCommand;
import iris.command.UnmarkTaskCommand;
import iris.exception.DoubleFieldException;
import iris.exception.IrisException;
import iris.exception.MissingFieldException;
import iris.exception.NoTaskException;
import iris.exception.UnknownTaskException;
import iris.task.Deadline;
import iris.task.Event;
import iris.task.Todo;

/**
 * Parses the user inputs
 */
public class Parser {
    enum CommandWord {
        help, bye, list, mark, unmark, delete, todo, deadline, event, reset, filter, find
    }

    private static void checkNotEmpty(String str, String message) throws MissingFieldException {
        if (str.isEmpty()) {
            throw new MissingFieldException(message);
        }
    }
    private static int getIndex(String input) throws IrisException {
        int i;
        try {
            i = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new UnknownTaskException();
        }
        return i;
    }

    private static String[] getFields(String input, int commandLength, String ... separators) throws IrisException {
        String[] fields = new String[separators.length + 1];
        try {
            input = input.substring(commandLength + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingFieldException("name");
        }
        String[] messages = new String[separators.length + 1];
        messages[0] = "name";
        for (int i = 0; i < separators.length; i++) {
            messages[i + 1] = separators[i].substring(1);
        }
        for (int i = 0; i < separators.length; i++) {
            String s = separators[i];
            String[] arr = input.split(s);
            if (arr.length < 2) {
                throw new MissingFieldException(messages[i + 1]);
            } else if (arr.length > 2) {
                throw new DoubleFieldException(messages[i + 1]);
            }
            checkNotEmpty(arr[0], s.substring(1));
            fields[i] = arr[0].trim();
            input = arr[1];
        }
        checkNotEmpty(input, messages[messages.length - 1]);
        fields[fields.length - 1] = input;
        return fields;
    }

    /**
     * parses the input of the user
     * @param input user input
     * @return Command corresponding to the input
     * @throws IrisException when there is a problem with the input
     */
    public static Command parse(String input) throws IrisException {
        String[] fields;
        CommandWord command;
        String commandString = input.split(" ")[0];
        int commandLength = commandString.length();
        try {
            command = CommandWord.valueOf(commandString);
        } catch (IllegalArgumentException e) {
            throw new NoTaskException();
        }
        switch (command) {
        case help:
            return new HelpCommand();
        case bye:
            return new ExitCommand();
        case list:
            return new ListCommand();
        case mark:
            return new MarkTaskCommand(getIndex(input));
        case unmark:
            return new UnmarkTaskCommand(getIndex(input));
        case delete:
            return new DeleteTaskCommand(getIndex(input));
        case reset:
            return new ResetCommand();
        case filter:
            try {
                String s = input.substring(7);
                String[] a = s.split("/to");
                if (a.length == 1) {
                    return new FilterCommand(s.trim());
                } else {
                    return new FilterCommand(a[0].trim(), a[1].trim());
                }
            } catch (IndexOutOfBoundsException e) {
                throw new MissingFieldException("Date for filter");
            }
        case find:
            return new FindCommand(getFields(input, commandLength)[0]);
        case todo:
            Todo todo = new Todo(getFields(input, commandLength)[0]);
            return new AddTaskCommand(todo);
        case deadline:
            fields = getFields(input, commandLength, "/by ");
            assert fields.length == 2 : "getFields should return 2 fields";
            Deadline deadline = new Deadline(fields[0], fields[1]);
            return new AddTaskCommand(deadline);
        case event:
            fields = getFields(input, commandLength, "/from ", "/to ");
            assert fields.length == 3 : "getFields should return 3 fields";
            Event event = new Event(fields[0], fields[1], fields[2]);
            return new AddTaskCommand(event);
        default:
            throw new NoTaskException();
        }
    }
}
