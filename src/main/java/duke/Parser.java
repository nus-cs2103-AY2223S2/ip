package duke;

import java.util.Arrays;

/**
 * Class that handles parsing of user input.
 */
public class Parser {

    private TaskList tasks;

    /**
     * Constructor for Parser class.
     * Returns Parser for Duke to parse user input.
     *
     * @param tasks TaskList object to reference for executing parsed commands.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses user input and executes it.
     *
     * @param commandLine User input in String.
     * @throws DukeException If user inputs a wrong command that is not specified or implemented.
     */
    public void parse(String commandLine) throws DukeException {

        String[] command = commandLine.split(" ");

        switch (command[0]) {

        case "list":
            tasks.printList();
            break;

        case "mark":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.markTask(command[1]);

            break;

        case "unmark":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.unmarkTask(command[1]);
            break;

        case "todo":
            if (command.length < 2) {
                throw new DukeException("The description of a todo cannot be empty!");
            }
            tasks.addTodo(command);
            break;

        case "deadline":
            int byIndex = Arrays.asList(command).indexOf("/by");
            if (command.length < 4 || byIndex == -1) {
                throw new DukeException("Too few arguments!");
            }
            if (byIndex == command.length - 1) {
                throw new DukeException("Check the format again!");
            }
            tasks.addDeadline(command, byIndex);
            break;

        case "event":
            int fromIndex = Arrays.asList(command).indexOf("/from");
            int toIndex = Arrays.asList(command).indexOf("/to");
            if (command.length < 6 || fromIndex == -1 || toIndex == -1) {
                throw new DukeException("Too few arguments!");
            }
            if (fromIndex + 1 >= toIndex || toIndex == command.length - 1) {
                throw new DukeException("Check the format again!");
            }
            tasks.addEvent(command, fromIndex, toIndex);
            break;

        case "delete":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.deleteTask(command[1]);
            break;

        case "find":
            StringBuilder keyword = new StringBuilder(command[1]);
            for (int i = 2; i < command.length; i++) {
                keyword.append(" ").append(command[i]);
            }
            tasks.find(keyword.toString());
            break;

        case "tag":
            if (command.length != 3) {
                throw new DukeException("Please check that your command is in the format: tag {task number} {tag name}");
            }
            int index = Integer.valueOf(command[1]);
            String tagName = command[2];
            tasks.tag(index, tagName);
            break;

        default:
            throw new DukeException("Invalid/Unknown command.");
        }

    }

}
