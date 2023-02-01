package duke.components;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public Parser() {

    }
    private static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(input.split(" ")));
        tokens.removeIf(s -> s.equals(" ") || s.equals(""));
        tokens.forEach(s -> s = s.trim());
        //System.out.println(tokens);
        return tokens;
    }
    public static Command parse(String userIn) throws DateTimeException, DukeException {
        ArrayList<String> tokens = tokenize(userIn);
        String key = tokens.get(0);
        Command ret = null;

        switch (key) {
            case "bye":
                ret = new ExitCommand(tokens);
                break;
            case "list":
                ret = new ListCommand(tokens);
                break;
            case "mark":
                ret = new MarkCommand(tokens);
                break;
            case "unmark":
                ret = new UnmarkCommand(tokens);
                break;
            case "delete":
                ret = new DeleteCommand(tokens);
                break;
            case "todo":
                ret = new AddToDoCommand(tokens);
                break;
            case "deadline":
                ret = new AddDeadlineCommand(tokens);
                break;
            case "event":
                ret = new AddEventCommand(tokens);
                break;
            default:
                throw new DukeException("I'm sorry, I could not understand that command.");
        }

        return ret;
    }

}
