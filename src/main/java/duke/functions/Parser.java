package duke.functions;

import duke.Duke;
import duke.ToDoList;

import duke.exceptions.DukeException;
import duke.exceptions.InputDukeException;

import duke.exceptions.IntParseDukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * A class that contains different static methods to deal with commands.
 */
public class Parser {
    private Parser() {
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
    public static String[] handleInput(
            String input, String regex, int limit, int minSize) throws DukeException {
        assert limit > 0 : "limit should be greater than 0";
        assert minSize > 0 : "minSize should be greater than 0";
        String[] subInputs = input.split(regex, limit);
        if (subInputs.length < minSize) {
            throw new InputDukeException();
        }
        return subInputs;
    }

    /**
     * Reads an array of String inputs and uses the first value of the array
     * to determine which operations shall be performed on the ToDoList object.
     * Returns a String that is the output message of the command performed.
     *
     * @param inputs The Array of String containing the operation to be performed
     *              and its additional parameters. The first value of the array
     *              should contain the type of operation to be carried out.
     * @param list The ToDoList object that the operations should be performed on.
     * @return A String that is the output message of the command performed.
     * @throws Exception If the given array of String contains insufficient or wrong
     *                   parameters or values not part of the possible commands.
     */
    public static String handleCommand(String[] inputs, ToDoList list, Duke duke) throws Exception {
        String command = inputs[0];
        int index;
        String reply;

        switch (command) {
        case "bye":
            reply = duke.shutDown();
            break;
        case "list":
            reply = Reply.getListMessage(list.toString());
            break;
        case "mark":
            index = Parser.handleIntParse(inputs[1]);
            list.markTask(index);
            reply = Reply.getTaskMarkMessage(list, index, command);
            break;
        case "unmark":
            index = Parser.handleIntParse(inputs[1]);
            list.unmarkTask(index);
            reply = Reply.getTaskMarkMessage(list, index, command);
            break;
        case "delete":
            index = Parser.handleIntParse(inputs[1]);
            Task removed = list.delete(index);
            reply = Reply.getAddDeleteMessage(list, removed, command);
            break;
        case "todo":
        case "event":
        case "deadline":
            reply = Parser.handleTaskCommand(inputs, list);
            break;
        case "find":
            reply = Reply.getFindResultMessage(list.find(inputs[1]), inputs[1]);
            break;
        default:
            throw new DukeException("The Duke does not understand your words!");
        }
        return reply;
    }

    /**
     * Reads a scanner and uses it to read and load the contents of the file
     * in the scanner to a ToDoList object. It returns the ToDoList object
     * loaded with the file's content.
     *
     * @param sc The Scanner containing the file to be read to load the ToDoList
     *           object.
     * @return A ToDoList that is obtained from reading the file contents.
     * @throws DukeException If the file contents contain invalid data.
     */
    public static ToDoList handleLoadCommand(Scanner sc) throws DukeException {
        ToDoList list = new ToDoList();
        while (sc.hasNext()) {
            String[] inputs = Parser.handleInput(sc.nextLine(), Pattern.quote("/+/"), 5, 3);
            String command = inputs[0];
            Task task;
            switch (command) {
            case "TODO":
                task = new ToDoTask(inputs[2]);
                Parser.handleLoadMark(task, inputs[1]);
                list.add(task);
                break;
            case "DEADLINE":
                task = new DeadlineTask(inputs[2], inputs[3]);
                Parser.handleLoadMark(task, inputs[1]);
                list.add(task);
                break;
            case "EVENT":
                task = new EventTask(inputs[2], inputs[3], inputs[4]);
                Parser.handleLoadMark(task, inputs[1]);
                list.add(task);
                break;
            }
        }
        return list;
    }

    private static void handleLoadMark(Task task, String command) {
        if (command.equals("DONE")) {
            task.markDone();
        }
    }

    /**
     * Reads a String input and returns the integer representation of that String.
     *
     * @param input The String to be parsed into an integer.
     * @return An integer that is obtained from parsing the given input.
     * @throws IntParseDukeException If the given String cannot be parsed as an integer value.
     */
    public static int handleIntParse(String input) throws IntParseDukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IntParseDukeException();
        }
    }

    /**
     * Reads an array of String inputs and uses the first value of the array
     * to determine which type of Task object should be added to the given ToDoList object.
     *
     * @param inputs The Array of String containing the operation to be performed
     *               and its additional parameters. The first value of the array should
     *               contain the type of Task object to be added to the ToDoList object.
     * @param list The ToDoList object that the newly created Task object should be added to.
     * @return A String that specifies the operation carried out on which Task object.
     * @throws DukeException If the given array of String contains insufficient or wrong
     *                       parameters or values not part of the possible commands.
     */
    public static String handleTaskCommand(String[] inputs, ToDoList list) throws DukeException {
        String command = inputs[0];

        if (inputs.length < 2) {
            throw new InputDukeException();
        }

        switch (command) {
        case "todo":
            ToDoTask toDoToAdd = new ToDoTask(inputs[1]);
            list.add(toDoToAdd);
            return Reply.getAddDeleteMessage(list, toDoToAdd, "add");
        case "deadline":
            String[] deadlineInputs = Parser.handleInput(inputs[1], " /by ", 2, 2);
            DeadlineTask deadlineToAdd = new DeadlineTask(deadlineInputs[0], deadlineInputs[1]);
            list.add(deadlineToAdd);
            return Reply.getAddDeleteMessage(list, deadlineToAdd, "add");
        case "event":
            String[] eventInputs = Parser.handleInput(inputs[1], " /from ", 2, 2);
            String[] subInputDurations = Parser.handleInput(eventInputs[1], " /to ", 2, 2);
            EventTask eventToAdd = new EventTask(eventInputs[0],
                    subInputDurations[0],
                    subInputDurations[1]);
            list.add(eventToAdd);
            return Reply.getAddDeleteMessage(list, eventToAdd, "add");
        default:
            throw new InputDukeException();
        }
    }
}
