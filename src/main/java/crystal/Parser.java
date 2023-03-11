package crystal;

import crystal.command.ByeCommand;
import crystal.command.Command;
import crystal.command.DeadlineCommand;
import crystal.command.DeleteCommand;
import crystal.command.EventCommand;
import crystal.command.FindCommand;
import crystal.command.ListCommand;
import crystal.command.MarkCommand;
import crystal.command.PriorityCommand;
import crystal.command.TodoCommand;
import crystal.command.UnmarkCommand;


/**
 * Represents the Parser task.
 */

public class Parser {

    /**
     * Returns the respective command object based on the command
     * the user enters.
     *
     * @param command User command
     * @throws CrystalException When user command is not recognised
     */
    public static Command parse(String command) throws CrystalException {
        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        default:
            if (command.contains("unmark")) {
                int num = getNumber(command);
                return new UnmarkCommand(num);
            } else if (command.contains("mark")) {
                int num = getNumber(command);
                return new MarkCommand(num);
            } else if (command.contains("todo")) {
                String stringNoTodo = getTodoDescription(command);
                return new TodoCommand(stringNoTodo);
            } else if (command.contains("deadline")) {
                String[] description = getDeadline(command);
                return new DeadlineCommand(description[0], description[1]);
            } else if (command.contains("event")) {
                String[] description = getEvent(command);
                return new EventCommand(description[0], description[1], description[2]);
            } else if (command.contains("find")) {
                String stringReplaceFind = command.replace("find", "");
                return new FindCommand(stringReplaceFind);
            } else if (command.contains("delete")) {
                int num = getNumber(command);
                return new DeleteCommand(num);
            } else if (command.contains("priority")) {
                String[] stringNoPriority = getPriority(command);
                return new PriorityCommand(Integer.parseInt(stringNoPriority[0]),
                        Integer.parseInt(stringNoPriority[1]));
            } else {
                throw new CrystalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }


    }

    /**
     * Returns the number portion in the command
     * @param command the command entered by the user
     * @return the number portion of the command
     */
    private static int getNumber(String command) {
        String getNum = command.replaceAll("[^0-9]", "");
        int num = Integer.parseInt(getNum);
        return num;
    }

    /**
     * Returns the description of the todo task
     * @param command the command entered by the user
     * @return the description of the todo task
     */
    private static String getTodoDescription(String command) throws CrystalException {
        String stringNoTodo = command.replace("todo", "");
        if (stringNoTodo.length() == 0) {
            throw new CrystalException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return stringNoTodo;
    }

    /**
     * Returns a string array containing the description and datetime of deadline task
     * @param command the command entered by the user
     * @return the string array containing the description and datetime of deadline task
     */
    private static String[] getDeadline(String command) {
        String stringNoDeadline = command.replace("deadline", "");
        int index = stringNoDeadline.lastIndexOf("/by");
        String description = stringNoDeadline.replace(stringNoDeadline.substring(index), ""); //description of task
        stringNoDeadline = stringNoDeadline.substring(index + 3); //date/time for by portion
        return new String[] { description, stringNoDeadline };
    }

    /**
     * Returns a string array containing the description of the event and the from and to datetimes
     * @param command the command entered by the user
     * @return string array containing the description of the event and the from and to datetimes
     */
    private static String[] getEvent(String command) {
        String stringNoEvent = command.replace("event", "");
        String[] arr = stringNoEvent.split("/");
        String subString = arr[0]; //description
        String temp = arr[1];
        String subStringNoFrom = temp.replace("from", ""); //from
        String tempOne = arr[2];
        String subStringNoTo = tempOne.replace("to", ""); //to
        return new String[] {subString, subStringNoFrom, subStringNoTo};
    }

    private static String[] getPriority(String command) {
        String stringNoPriority = command.replace("priority item", "");
        String[] arr = stringNoPriority.split(" level ");
        String noItem = arr[0].replace("item", "");
        String nolevel = arr[1];
        return new String[] {noItem.trim(), nolevel.trim()};
    }




}

