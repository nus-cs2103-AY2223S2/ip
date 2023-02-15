package runner;
import command.*;
import gui.Ui;

/**
 * Parser to parse the command process the necessary actions.
 */
public class Parser {
    private final Riddle riddle;

    /**
     * Constructor for Parser.
     * @param riddle a Riddle chat-bot to work on.
     */
    public Parser(Riddle riddle) {
        this.riddle = riddle;
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
            case "hi":
                return Ui.start();
            case "bye":
            case"goodbye":
                return Ui.ending();
            case "list":
                return Ui.showList(riddle.taskList, 1);
            case "undo":
                return new Undo(riddle).execute();
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
                return new Mark(this.riddle).execute(arg);
            case "unmark":
                return new Unmark(this.riddle).execute(arg);
            case "todo":
            case "deadline":
            case "event":
                return new Add(this.riddle).execute(first, arg);
            case "delete":
                return new Delete(this.riddle).execute(arg);
            case "find":
                return new Find(this.riddle).execute(arg);
            default:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }
}

