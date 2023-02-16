package genie.main;

import genie.command.*;
import genie.exception.GenieException;
import genie.exception.EmptyInputException;
import genie.exception.InvalidInputException;

/**
 * Deals with making sense of the user command on Genie. Creates the required action upon retrieving a user command.
 */
public class Parser {
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String HELP = "help";

    /**
     * A constructor for Parser class.
     */
    public Parser() {}

    /**
     * Takes in a user input and returns its corresponding command for further action by Genie.
     * @param input user input
     * @return <Code>Command</Code> aligning to the user's input
     * @throws GenieException if error occurs in parsing
     */
    public Command parse(String input) throws GenieException {
        String lowerCaseInput = input.toLowerCase();
        boolean isBye = isBye(lowerCaseInput);
        boolean isList = isList(lowerCaseInput);
        boolean isHelp = isHelp(lowerCaseInput);
        boolean isTaskRelated = isTaskRelated(lowerCaseInput);
        if (isBye) {
            return new ExitCommand(); // todo exit when bye
        } else if (isList) {
            return new ListCommand();
        } else if (isHelp) {
            return new HelpCommand();
        } else if (isTaskRelated) {
            int index;
            String command = isolateCommand(lowerCaseInput);
            String[] inputWords = inputWords(lowerCaseInput);
            switch (command) {
            case MARK:
                index = parseInt(inputWords[1]);
                return new MarkCommand(index);
            case UNMARK:
                index = parseInt(inputWords[1]);
                return new UnmarkCommand(index);
            case DELETE:
                index = parseInt(inputWords[1]);
                return new DeleteCommand(index);
            case TODO:
            case DEADLINE:
            case EVENT:
                int inputSize = inputWords.length;
                if (inputSize == 1) {
                    throw new EmptyInputException(command);
                }
                return new AddCommand(command, lowerCaseInput);
            case FIND:
                String keyword = lowerCaseInput.replace(FIND + " ", "");
                int keywordSize = inputWords.length;
                if (keywordSize == 1) {
                    throw new EmptyInputException(command);
                }
                return new FindCommand(keyword);
            }
        }
        throw new InvalidInputException();
    }

    /**
     * Checks if command is 'bye'.
     * @param command
     * @return True if is 'bye', false otherwise.
     */
    public boolean isBye(String command) {
        return command.equals(BYE);
    }

    /**
     * Checks if command is 'list'.
     * @param command
     * @return True if is 'list', false otherwise.
     */
    public boolean isList(String command) {
        return command.equals(LIST);
    }

    /**
     * Checks if command is 'help'.
     * @param command
     * @return True if is 'help', false otherwise.
     */
    public boolean isHelp(String command) {
        return command.equals(HELP);
    }

    /**
     * Checks if command is task related (unmark, mark, delete, to do, event, deadline, find).
     * @param input
     * @return True if is task related, false otherwise.
     */
    public boolean isTaskRelated(String input) {
        String command = isolateCommand(input);
        boolean isTaskRelated = command.equals(MARK) ||
                command.equals(UNMARK) ||
                command.equals(DELETE) ||
                command.equals(TODO) ||
                command.equals(EVENT) ||
                command.equals(DEADLINE) ||
                command.equals(FIND);
        return isTaskRelated;
    }

    /**
     * Retrieves only the command from the entire user input.
     * @param input
     * @return command
     */
    public String isolateCommand(String input) {
        return inputWords(input)[0];
    }

    /**
     * Returns user input in an array, separated by " ".
     * @param input
     * @return user input in a String array
     */
    public String[] inputWords(String input) {
        return input.split(" ");
    }

    /**
     * Parses string index to int.
     * @param index
     * @return index
     */
    public int parseInt(String index) {
        int parsedInt = Integer.parseInt(index);
        return parsedInt;
    }
}
