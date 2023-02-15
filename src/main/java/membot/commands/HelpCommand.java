package membot.commands;

import java.util.Arrays;
import java.util.stream.Stream;

import membot.view.Printable;

/**
 * Represents a command that provides users help with using the application.
 */
public class HelpCommand extends Command {
    /**
     * Generates a <code>HelpCommand</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    protected HelpCommand(Printable ui) {
        super(ui);
        this.ui = ui;
    }

    /**
     * Prints out a list of useful commands for Membot.
     */
    @Override
    public void execute() {
        this.ui.println(
                Stream.concat(
                        Stream.of("Welcome to Membot! Here are some things you can do:", ""),
                        Arrays.stream(CommandType.class.getEnumConstants())
                                .map(x -> x.toString().toLowerCase() + " - " + getCommandDescription(x))
                ).toArray(String[]::new)
        );
    }

    private String getCommandDescription(CommandType command) {
        switch (command) {
        case HELP:
            return "view all available commands";
        case LIST:
            return "view all tasks";
        case FIND:
            return "find a task using a keyword";
        case SORT:
            return "Sort all tasks based on option specified";
        case TODO:
            return "create a TODO task";
        case DEADLINE:
            return "create a DEADLINE task";
        case EVENT:
            return "create an EVENT task";
        case DELETE:
            return "delete a task";
        case DONE:
            return "mark a task as completed";
        case UNDONE:
            return "mark a task as incomplete";
        case BYE:
            return "exit Membot";
        default:
            return "";
        }
    }
}
