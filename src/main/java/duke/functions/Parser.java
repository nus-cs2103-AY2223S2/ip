package duke.functions;

import duke.ToDoList;
import duke.exceptions.DukeException;
import duke.exceptions.InputDukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

/**
 * A class that contains different static methods to deal with user's input.
 */
public class Parser {
    private  Parser() {
    }

    /**
     * Returns an array of String after splitting the given String input by the given regex.
     * The maximum number of Strings to be split must be indicated by the user and should the
     * number of Strings obtained after splitting be less than the indicated minimum number
     * of Strings, the method will throw a DukeException instead.
     *
     * @param input The String to be split.
     * @param regex The delimiting regular expression.
     * @param limit The maximum number of Strings obtained from splitting.
     * @param minSize The minimum number of Strings to be obtained from splitting.
     * @return The array of String after splitting.
     * @throws DukeException  If the number of String obtained after
     *                        splitting is less than minimum number of String.
     */
    public static String[] inputHandler(String input, String regex, int limit, int minSize) throws DukeException {
        String[] sub = input.split(regex, limit);
        if (sub.length < minSize) {
            throw new InputDukeException();
        }
        return sub;
    }

    /**
     * The method reads an array of String input and uses the first value of the array
     * to determine which operations shall be performed on the ToDoList object.
     * Returns a boolean value indicating if the main program should shut down
     * or continue running.
     *
     * @param input The Array of String containing the operation to be performed
     *              and its additional parameters. The first value of the array
     *              should contain the type of operation to be carried out.
     * @param ls The ToDoList object that the operations should be performed on.
     * @return A boolean value indicating if the program should end or continue.
     * @throws Exception If the given array of String contains insufficient or wrong
     *                   parameters or values not part of the possible commands.
     */
    public static boolean commandHandler(String[] input, ToDoList ls) throws Exception {
        String command = input[0];
        int index;

        switch (command) {
        case "bye":
            return true;
        case "list":
            UI.listMsg(ls.toString());
            break;
        case "mark":
            index = Integer.parseInt(input[1]);
            ls.markTask(index); //numbersformatexception to be handled
            UI.taskMarking(ls, index, command);
            break;
        case "unmark":
            index = Integer.parseInt(input[1]);;
            ls.unmarkTask(index); //numbersformatexception to be handled
            UI.taskMarking(ls, index, command);
            break;
        case "delete":
            index = Integer.parseInt(input[1]);
            Task removed = ls.delete(index); //numbersformatexception to be handled
            UI.taskAddDelete(ls, removed, command);
            break;
        case "todo":
        case "event":
        case "deadline":
            Parser.taskCommandHandler(input, ls);
            break;
        default:
            throw new DukeException("The Duke does not understand your words!");
        }
        return false;
    }

    /**
     * The method reads an array of String input and uses the first value of the array
     * to determine which type of Task object should be added to the given ToDoList object.
     *
     * @param input The Array of String containing the operation to be performed
     *              and its additional parameters. The first value of the array should
     *              contain the type of Task object to be added to the ToDoList object.
     * @param ls The ToDoList object that the newly created Task object should be added to.
     * @throws DukeException If the given array of String contains insufficient or wrong
     *                       parameters or values not part of the possible commands.
     */
    public static void taskCommandHandler(String[] input, ToDoList ls) throws DukeException {
        String command = input[0];

        if (input.length < 2) {
            throw new InputDukeException();
        }
        if (command.equals("todo")) {
            ToDoTask toAdd = new ToDoTask(input[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("deadline")) {
            String[] sub = Parser.inputHandler(input[1], " /by ", 2, 2);
            DeadlineTask toAdd = new DeadlineTask(sub[0], sub[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("event")) {
            String[] sub = Parser.inputHandler(input[1], " /from ", 2, 2);
            String[] subDuration = Parser.inputHandler(sub[1], " /to ", 2, 2);
            EventTask toAdd = new EventTask(sub[0], subDuration[0], subDuration[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
        }
    }
}
