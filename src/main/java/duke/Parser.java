package duke;
import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddTodo;
import duke.commands.Bye;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.Unmark;

/**
 * The parse method takes in a string and returns a command object
 */
public class Parser {
    // private enum CommandType {
    //     DEADLINE,
    //     EVENT,
    //     BYE,
    //     LIST,
    //     MARK,
    //     UNMARK,
    //     DELETE,
    //     FIND
    //}
    /**
     * parse takes in the user input and returns a Command for the program to execute
     * @param input the user input
     * @return A command object
     */
    public static Command parse(String input) throws DukeException {
        String[] inputwords = input.split(" ");
        String commandinput = inputwords[0].toUpperCase();

        switch(commandinput) {

        case "TODO":
            if (inputwords.length < 2) {
                throw new DukeException("Description is missing");
            }
            return new AddTodo(input);

        case "DEADLINE":
            if (inputwords.length < 2) {
                throw new DukeException("Description is missing");
            }
            return new AddDeadline(input);

        case "EVENT":
            if (inputwords.length < 2) {
                throw new DukeException("Description is missing");
            }
            return new AddEvent(input);

        case "DELETE":
            if (inputwords.length < 2) {
                throw new DukeException("Index for delete command is missing");
            }
            return new Delete(inputwords[1]);

        case "MARK":
            if (inputwords.length < 2) {
                throw new DukeException("Index for mark command is missing");
            }
            return new Mark(inputwords[1]);

        case "UNMARK":
            if (inputwords.length < 2) {
                throw new DukeException("Index for unmark command is missing");
            }
            return new Unmark(inputwords[1]);

        case "BYE":
            return new Bye();

        case "LIST":
            return new List();

        case "FIND":
            if (inputwords.length < 2) {
                throw new DukeException("keyword for find command is missing");
            }
            return new Find(inputwords[1]);

        default:
            throw new DukeException("invalid command");
        }

    }
}
