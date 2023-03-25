package brotherbot.parser;

import brotherbot.commands.*;

import brotherbot.exceptions.BroException;
import brotherbot.storage.TaskList;

import java.time.Duration;
import java.time.format.DateTimeParseException;

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
    public Command parse(String input, TaskList storage) throws BroException {
        validateInput(input, storage);
        Command command;
        if (input.equalsIgnoreCase("bye")) {
            command = new ExitCommand(input);
        } else if (input.equalsIgnoreCase("display")) {
            command = new DisplayCommand(input);
        } else if (input.length() >= 6 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            command = new MarkTaskCommand(input);
        } else if (input.length() >= 8 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            command = new DeleteCommand(input);
        } else if (input.length() >= 6 && input.substring(0, 4).equalsIgnoreCase("find")) {
            command = new FindCommand(input);
        } else if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("todo")) {
            command = new AddTaskCommand(input);
        } else if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("event")) {
            command = new AddTaskCommand(input);
        } else if (input.length() > 8 && input.substring(0, 8).equalsIgnoreCase("deadline")) {
            command = new AddTaskCommand(input);
        } else if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("free")) {
            command = new FreeTimeCommand(input);
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("help")) {
            command = new HelpCommand(input);
        }
        else {
            throw new BroException("OOPS! wrong command la bro, Input 'help' to see available commands!");
        }
        return command;
    }

    /**
     * Checks if inputs are valid.
     */
    private static void validateInput(String input, TaskList storage) throws BroException {

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
            throw new BroException("OOPS wrong format my brother! consider this format: \nevent xxx /from xxx /to xxx");
        }

        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("free")) {
            try {
                Duration.parse("PT" + input.substring(5));
            } catch (DateTimeParseException e) {
                throw new BroException("Brother, input your free duration following this example: 'xHyyM', replace x " +
                        "with number of hours and yy with number of minutes! ");
            }
        }

    }
}
