package duke.command;

import duke.exception.MissingDescriptionException;
import duke.tasklist.TaskList;

/**
 * Represents an input Parser.
 * A Parser decodes User input and allows Duke to process these inputs.
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList arrList) {
        this.tasks = arrList;
    }
    /**
     * Checks if the command is equals to the exit command
     * to enable the program to exit.
     *
     * @param command String representation of User input.
     * @return boolean that determines whether Duke continues to run.
     */
    public boolean isExit(String command) {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }
    /**
     * Returns a String representation of the text to return to the user
     * upon parsing the input.
     *
     * @param command String representation of User input.
     * @return String that represents the program output.
     */
    public String parse(String command) {
        String toReturn;
        switch(command) {
        case "bye":
            return "Chenquieh. Hope to see you again Premier Azamat!";
        case "list":
            String taskString = this.tasks.printList();
            toReturn = taskString;
            return toReturn;
        default:
            String[] arrOfStr = command.split(" ", 2);
            try {
                validateCmd(arrOfStr[1]);
            } catch (MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }
            switch (arrOfStr[0]) {
            case "delete":
                DeleteCommand toDelete = new DeleteCommand(this.tasks);
                toReturn = toDelete.execute(arrOfStr[1]);
                return toReturn;
            case "unmark":
                UnmarkCommand toUnmark = new UnmarkCommand(this.tasks);
                toReturn = toUnmark.execute(arrOfStr[1]);
                return toReturn;
            case "mark":
                MarkCommand toMark = new MarkCommand(this.tasks);
                toReturn = toMark.execute(arrOfStr[1]);
                return toReturn;
            case "find":
                FindCommand toFind = new FindCommand(this.tasks);
                toReturn = toFind.execute(arrOfStr[1]);
                return toReturn;
            case "todo":
            case "deadline":
            case "event":
                AddCommand toAdd = new AddCommand(this.tasks, arrOfStr);
                toReturn = toAdd.execute();
                return toReturn;
            default:
                toReturn = "Sorry your command is invalid";
                return toReturn;
            }
        }
    }

    /**
     * Ensures the validity of User's input command.
     *
     * @param cmd String representation of the command.
     * @throws MissingDescriptionException If User input lacks a body.
     */
    public static void validateCmd(String cmd) throws MissingDescriptionException {
        if (cmd.length() == 0) {
            throw new MissingDescriptionException("You need to " +
                    "be more specific");
        }

    }
}

