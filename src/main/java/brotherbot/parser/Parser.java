package brotherbot.parser;

import brotherbot.commands.AddTaskCommand;
import brotherbot.commands.Command;
import brotherbot.commands.DeleteCommand;
import brotherbot.commands.DisplayCommand;
import brotherbot.commands.ExitCommand;
import brotherbot.commands.MarkTaskCommand;

import brotherbot.exceptions.BroException;
import brotherbot.storage.TaskList;

public class Parser {

    /**
     * Constructor to create a Parser object.
     */
    public Parser() {
    }

    /**
     * Parse valid user input into corresponding Command objects.
     *
     * @return Command object to be executed.
     * @throws BroException if invalid inputs are parsed.
     */
    public static Command parse(String input, TaskList storage) throws BroException {
        validateInput(input, storage);
        Command command;
        if (input.equalsIgnoreCase("bye")) {
            command = new ExitCommand(input);
        } else if (input.equalsIgnoreCase("display")) {
            command = new DisplayCommand(input);
        } else if (input.length() >= 6 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            command = new MarkTaskCommand(input);
        } else if (input.length() > 7 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            command = new DeleteCommand(input);
        } else {
            command = new AddTaskCommand(input);
        }
        return command;
    }

    /**
     * Checks if inputs are valid.
     */
    private static void validateInput(String input, TaskList storage) throws BroException {
        if (!input.contains("todo") && !input.contains("event") && !input.contains("display") && !input.contains("deadline") && !input.contains("mark") && !input.contains("bye") && !input.contains("delete")) {
            throw new BroException("OOPS! invalid command la bro");
        }
        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("todo") && input.length() <= 5) {
            throw new BroException("OOPS wrong format my brother! consider this format: \ntodo xxx");
        }

        if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("event") && (!input.contains("/from") || input.indexOf("/from") == 6 || !input.contains("/to") || input.indexOf("/from") > input.indexOf("/to"))) {
            throw new BroException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }

        if (input.length() > 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            try {
                Integer.parseInt(input.substring(7));
            } catch(NumberFormatException e) {
                throw new BroException("OOPS wrong format my brother! consider this format: \ndelete INSERT_NUMBER");
            }
            if (Integer.parseInt(input.substring(7)) > storage.size()) {
                throw new BroException("OOPS inserted number is invalid");
            }
        }

        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            try {
                Integer.parseInt(input.substring(5));
            } catch(NumberFormatException e) {
                throw new BroException("OOPS wrong format my brother! consider this format: \ndelete INSERT_NUMBER");
            }
            if (Integer.parseInt(input.substring(5)) > storage.size()) {
                throw new BroException("OOPS inserted number is invalid");
            }
        }
        if (input.length() > 8 && input.substring(0, 8).equalsIgnoreCase("deadline") && (!input.contains("/by") || input.indexOf("/by") == 9)) {
            throw new BroException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }
    }
}
