package runner;
import command.*;
import gui.Ui;

/**
 * Parser to parse the command process the necessary actions.
 */
public class Parser {
    private final Duke duke;

    /**
     *
     * Constructor for Parser.
     * @param duke a Duke chat-bot to work on.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Specifically handle user command.
     * @param input The information given by the user.
     * @returns A response to the user command.
     */
    public String handle(String input) {
        if (!input.contains(" ")) {
            switch (input) {
            case "hello":
                return Ui.start();
            case "bye":
                return Ui.ending();
            case "list":
                return Ui.showList(duke.taskList, 1);
            case "undo":
                return new Undo(duke).execute();
            default:
                return "Not Smart to Understand -_-";
            }
        }
        try {
            String[] segments = input.split(" ", 2);
            String first = segments[0];
            String arg = segments[1];
            switch (first) {
            case "mark":
                return new Mark(this.duke).execute(arg);
            case "unmark":
                return new Unmark(this.duke).execute(arg);
            case "todo":
            case "deadline":
            case "event":
                return new Add(this.duke).execute(first, arg);
            case "delete":
                return new Delete(this.duke).execute(arg);
            case "find":
                return new Find(this.duke).execute(arg);
            default:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }
}

