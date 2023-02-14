package genie.main;

import genie.command.*;
import genie.exception.DukeException;
import genie.exception.EmptyInputException;
import genie.exception.InvalidInputException;

/**
 * Deals with making sense of the user command on Genie. Creates the required action upon retrieving a user command.
 */
public class Parser {
    /**
     * A constructor for Parser class.
     */
    public Parser() {}

    /**
     * Takes in a user input and returns its corresponding command for further action by Genie.
     * @param in user input
     * @return <Code>Command</Code> aligning to the user's input
     * @throws DukeException if error occurs in parsing
     */
    public Command parse(String in) throws DukeException {
        String i = in.toLowerCase();
        if (i.equals("bye")) {
            return new ExitCommand();
        } else if (i.equals("list")) {
            return new ListCommand();
        } else {
            String command = i.split(" ")[0];
            int index = -1;
            switch (command) {
            case "mark":
                index = Integer.parseInt(i.split(" ")[1]); // todo handle exception when marked alr && marking a non existent index
                return new MarkCommand(index);
            case "unmark":
                index = Integer.parseInt(i.split(" ")[1]); // todo same as mark
                return new UnmarkCommand(index);
            case "delete":
                index = Integer.parseInt(i.split(" ")[1]); // todo handle when deleting non existent index
                return new DeleteCommand(index);
            case "todo":
            case "deadline":
            case "event":
                String taskType = i.split(" ")[0];
                if (i.split(" ").length == 1) {
                    throw new EmptyInputException(taskType);
                }
                return new AddCommand(taskType, i);
            case "find":
                String keyword = i.replace("find ", ""); //todo handle empty keyword
                return new FindCommand(keyword);
            default:
                throw new InvalidInputException();
            }
        }
    }
}
