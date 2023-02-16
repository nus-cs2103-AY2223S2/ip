package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindDeadlineCommand;
import duke.commands.FindKeywordCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;

/**
 * A class that parses user's input into commands.
 */
public class Parser {

    /**
     * Parses user input as adder command for execution.
     *
     * @param userInput user's input string.
     * @return A command with respect to the user's input.
     */
    public static Command parseAdderCommand(String userInput) {
        String[] inputs = userInput.split(" ");
        String taskType = inputs[0];
        switch (taskType) {
        case "todo": {
            return new AddToDoCommand(userInput);
        }
        case "deadline": {
            return new AddDeadlineCommand(userInput);
        }
        case "event": {
            return new AddEventCommand(userInput);
        }
        default:
            return new Command();
        }
    }

    /**
     * Parses user input as marker command for execution.
     * @param userInput user's input string.
     * @param isMark A boolean to indicate if it is mark or unmark command.
     * @return A command with respect to the user's input.
     */
    public static Command parseMarkerCommand(String userInput, Boolean isMark) {
        int idx = Integer.parseInt(userInput.split(" ")[1]);
        if (isMark) {
            return new MarkCommand(idx);
        }
        return new UnmarkCommand(idx);
    }

    /**
     * Parses user input as command for execution.
     *
     * @param userInput user's input string
     * @return A command with respect to the user's input
     */
    public static Command parse(String userInput) {
        Pattern mark = Pattern.compile("mark [0-9]+");
        Pattern unmark = Pattern.compile("unmark [0-9]+");
        Pattern delete = Pattern.compile("delete [0-9]+");
        Pattern find = Pattern.compile("find .*");
        Pattern findBy = Pattern.compile("findBy .*");
        Matcher matchMark = mark.matcher(userInput);
        Matcher matchUnmark = unmark.matcher(userInput);
        Matcher matchDelete = delete.matcher(userInput);
        Matcher matchFind = find.matcher(userInput);
        Matcher matchFindBy = findBy.matcher(userInput);

        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (matchMark.matches() || matchUnmark.matches()) {
            return parseMarkerCommand(userInput, matchMark.matches());
        } else if (matchDelete.matches()) {
            int idx = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(idx);
        } else if (matchFind.matches()) {
            String keyword = userInput.split(" ")[1];
            return new FindKeywordCommand(keyword);
        } else if (matchFindBy.matches()) {
            String by = userInput.split(" ")[1];
            return new FindDeadlineCommand(by);
        } else {
            return parseAdderCommand(userInput);
        }
    }
}
