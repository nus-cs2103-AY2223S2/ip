package duke;
import duke.command.*;

/**
 * Parses a Command based on the string input.
 */
public class Parser {

    /**
     * Returns a Command respective to each command input.
     * @param fullCommand the command that needs to be parsed
     * @return the command that needs to be executed
     */
    public static Command parse(String fullCommand) {
        String[] fullCommandArr = fullCommand.replaceFirst(" ", "#").split("#");
        String command = fullCommandArr[0];
        String content = "";
        if (fullCommandArr.length > 1) {
            content = fullCommandArr[1];
        }
        switch (command) {
        case "bye":
            return new EndCommand();
        case "list":
            return new PrintListCommand();
        case "mark":
            int markIdx = Integer.parseInt(content);
            return new MarkCommand(markIdx);
        case "unmark":
            int unmarkIdx = Integer.parseInt(content);
            return new UnmarkCommand(unmarkIdx);
        case "todo":
            return new TodoCommand(content);
        case "deadline":
            return new DeadlineCommand(content);
        case "event":
            return new EventCommand(content);
        case "delete":
            int deleteIdx = Integer.parseInt(content);
            return new DeleteCommand(deleteIdx);
        case "guide":
            return new GuideCommand();
        default:
            return new UnknownCommand();
        }
    }
}
