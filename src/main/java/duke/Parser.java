package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.AddTaskCommand;
import duke.command.FindCommand;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    static final String VALIDCOMMANDS = "bye, list, mark, unmark, delete, todo, deadline, event, find";
    static final HashMap<String, String> CORRECTFORMAT = new HashMap<>(Map.of(
            "list", "list",
            "bye", "bye",
            "todo", "todo THE TASK",
            "deadline", "deadline THE TASK /by yyyy-mm-ddThh:mm:ss",
            "event", "event THE TASK /from TIME /to TIME",
            "mark", "mark NUMBER",
            "unmark", "unmark NUMBER",
            "delete", "delete NUMBER",
            "find", "find WORDS"
    ));
    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ");
        if (!CORRECTFORMAT.containsKey(words[0])) {
            String noValid = "I'm sorry, but I don't know what that means. My valid commands are:\n";
            noValid += VALIDCOMMANDS;
            throw new DukeException(noValid);
        }
        Command cmd = new Command();
        try {
            switch (words[0]) {
                case "bye":
                    cmd = new ExitCommand();
                    break;
                case "list":
                    cmd = new ListCommand();
                    break;
                case "mark":
                    cmd = new MarkCommand(words[1]);
                    break;
                case "unmark":
                    cmd = new UnmarkCommand(words[1]);
                    break;
                case "delete":
                    cmd = new DeleteCommand(words[1]);
                    break;
                case "find":
                    cmd = new FindCommand(words[1]);
                    break;
                default:    // for tasks
                    cmd = new AddTaskCommand(fullCommand);
            }
        } catch (Exception e) {
            throw new DukeException("Please enter in the correct format:\n" +
                    CORRECTFORMAT.get(words[0]));
        }
        return cmd;
    }
}
