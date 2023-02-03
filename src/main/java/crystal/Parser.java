package crystal;

import crystal.command.Command;
import crystal.command.ListCommand;
import crystal.command.UnmarkCommand;
import crystal.command.MarkCommand;
import crystal.command.TodoCommand;
import crystal.command.DeadlineCommand;
import crystal.command.EventCommand;
import crystal.command.ByeCommand;
import crystal.command.DeleteCommand;
import crystal.command.FindCommand;

/**
 * Represents the Parser task.
 *
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
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.contains("unmark")) {
            String getNum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getNum);
            return new UnmarkCommand(num);
        } else if (command.contains("mark")) {
            String getNum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getNum);
            return new MarkCommand(num);
        } else if (command.contains("todo")) {
            String stringNoTodo = command.replace("todo", "");
            if (stringNoTodo.length() == 0) {
                throw new CrystalException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new TodoCommand(stringNoTodo);
        } else if (command.contains("deadline")) {
            String stringNoDeadline = command.replace("deadline", "");
            int index = stringNoDeadline.lastIndexOf("/by");
            String description = stringNoDeadline.replace(stringNoDeadline.substring(index), ""); //description of task
            stringNoDeadline = stringNoDeadline.substring(index + 3); //date/time for by portion
            return new DeadlineCommand(description, stringNoDeadline);
        } else if (command.contains("event")) {
            String stringNoEvent = command.replace("event", "");
            String[] arr = stringNoEvent.split("/");
            String subString = arr[0]; //description
            String temp = arr[1];
            String subStringNoFrom = temp.replace("from", ""); //from
            String tempOne = arr[2];
            String subStringNoTo = tempOne.replace("to", ""); //to
            return new EventCommand(subString, subStringNoFrom, subStringNoTo);
        } else if (command.contains("find")) {
            String stringReplaceFind = command.replace("find", "");
            return new FindCommand(stringReplaceFind);
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.contains("delete")) {
            String getNum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getNum);
            return new DeleteCommand(num);
        } else {
            throw new CrystalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

