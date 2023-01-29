package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InsufficientAruments;
import duke.exception.UnknownCommand;
import duke.task.TaskList;

/**
 * This class implements the parsing of command line arguments, and
 * create and put the corresponding command with the parsed input
 * arguments into the command queue.
 */
public class Parser {
    private static CommandQueue queue = new CommandQueue();

    /**
     * Creates a command according to the user input, parse it,
     * and pass the relevant input into the command created,
     * and store the command in the command queue.
     * @param input
     * @throws DukeException
     */
    public static void createCommand (String input) throws DukeException {
        switch (input){
            default : {
                if (input.matches("mark+ [0-9]+")) {
                    queue.add(new Mark(input));
                } else if (input.matches("unmark+ [0-9]+")) {
                    queue.add(new Unmark(input));
                } else if (input.matches("delete+ [0-9]+")) {
                    queue.add(new Delete(input));
                } else if (input.matches("^deadline\\s.*$") || input.matches("^event\\s.*$") || input.matches("^todo\\s.*$")) {
                    if (input.split(" ").length < 2) {
                        throw new InsufficientAruments("OOPS!!! The description of a " +
                                input.split(" ")[0] + " cannot be empty.");
                    } else {
                        queue.add(new ExecuteCommandQueue(input)); //if the command is a valid task creation command
                    }
                } else {
                    throw new UnknownCommand("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                break;
            }
            case "list": {
                queue.add(new List(input));
                break;
            }
            case "bye" : {
                queue.add(new Bye(input));
                break;
            }
        }
    }

    /***
     * Executes all commands waiting in the queue.
     * This method returns nothing.
     * @param list
     */
    public static void executeQueue (TaskList list){
        queue.executeQueue(list);
    }
}
