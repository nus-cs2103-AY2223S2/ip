package duke.parser;

import duke.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput user's input string
     * @return A command with respect to the user's input
     */

    public static Command parse(String userInput) {
        Pattern mark = Pattern.compile("mark [0-9]+");
        Pattern unmark = Pattern.compile("unmark [0-9]+");
        Pattern delete = Pattern.compile("delete [0-9]+");
        Pattern find = Pattern.compile("find .*");
        Matcher matchMark = mark.matcher(userInput);
        Matcher matchUnmark = unmark.matcher(userInput);
        Matcher matchDelete = delete.matcher(userInput);
        Matcher matchFind = find.matcher(userInput);

        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (matchMark.matches()) {
            int idx = Integer.parseInt(userInput.split(" ")[1]);
            return new MarkCommand(idx);
        } else if (matchUnmark.matches()) {
            int idx = Integer.parseInt(userInput.split(" ")[1]);
            return new UnmarkCommand(idx);
        } else if (matchDelete.matches()) {
            int idx = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(idx);
        } else if (matchFind.matches()) {
            String keyword = userInput.split(" ")[1];
            return new FindCommand(keyword);
        } else {
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
    }
}
