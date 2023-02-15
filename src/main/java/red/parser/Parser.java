package red.parser;

import red.exception.RedException;

import red.task.DeadlineTask;
import red.task.EventTask;
import red.task.ToDoTask;

import red.command.AddCommand;
import red.command.Command;
import red.command.DeleteCommand;
import red.command.ExitCommand;
import red.command.ListCommand;
import red.command.MarkCommand;
import red.command.UnmarkCommand;
import red.command.FindCommand;

/**
 * This class interprets the inputs by the user.
 */
public class Parser {



    /**
     * Parses through the user string input to understand what action should be taken next.
     *
     * @param input The user's instructions to the program.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     */
    public static Command parse(String input) throws RedException {
        String[] arrOfStr = input.split(" ", 2);
        assert arrOfStr.length >= 1;


        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.equals("list")) {
                return new ListCommand();

        } else if (arrOfStr[0].equals("find")) {
            if(arrOfStr.length <= 1) {
                throw new RedException("Specification of which task to find is missing\n");
            }
            return new FindCommand(arrOfStr[1]);

        } else if (arrOfStr[0].equals("mark")) {
            if(arrOfStr.length <= 1) {
                throw new RedException("Specification of which task to mark is missing\n");
            }
            Integer index = Integer.valueOf(arrOfStr[1]) - 1;
            return new MarkCommand(index);

        } else if (arrOfStr[0].equals("unmark")) {
            if(arrOfStr.length <= 1) {
                throw new RedException("Specification of a which task to unmark is missing\n");
            }
            Integer index = Integer.valueOf(arrOfStr[1]) - 1;
            return  new UnmarkCommand(index);

        } else if (arrOfStr[0].equals("deadline")) {
            DeadlineTask newDeadlineTask = null;
            if (arrOfStr.length <= 1) {
                throw new RuntimeException("Specification of the DeadlineTask is missing\n");
            }
            String[] deadstr = arrOfStr[1].split("/by ", 2);
            if (deadstr.length != 2) {
                throw new RuntimeException("Specification of the DeadlineTask is missing\n");
            }
            String[] timestr = deadstr[1].split(" ", 2);
            if (timestr.length < 1) {
                throw new RuntimeException("Specification of the DeadlineTask is missing\n");
            }

            if (timestr.length == 2 && timestr[1].isEmpty()) {
                newDeadlineTask = new DeadlineTask(deadstr[0].trim(),timestr[0]);
            } else if (timestr.length == 2) {
                System.out.println(timestr[1].isEmpty());
                newDeadlineTask = new DeadlineTask(deadstr[0].trim(),timestr[0],timestr[1]);
            } else if (timestr.length == 1) {
                newDeadlineTask = new DeadlineTask(deadstr[0].trim(),timestr[0]);
            }

            return new AddCommand(newDeadlineTask);

        } else if(arrOfStr[0].equals("todo")) {
            if(arrOfStr.length <= 1) {
                throw new RuntimeException("Specification of the ToDoTask is missing\n");
            }
            ToDoTask NewToDoTask = new ToDoTask(arrOfStr[1].trim());
            return new AddCommand(NewToDoTask);

        } else if(arrOfStr[0].equals("event")) {
            if(arrOfStr.length <= 1) {
                throw new RuntimeException("Specification of the EventTask is missing\n");
            }
            String[] eventStr = arrOfStr[1].split("/from ", 2);
            if(eventStr.length != 2) {
                throw new RuntimeException("Specification of the EventTask is missing\n");
            }
            String[] dateTimeStr = eventStr[1].split(" /to ", 2);
            if(dateTimeStr.length != 2) {
                throw new RuntimeException("Specification of the EventTask is missing\n");
            }
            EventTask NewEventTask = new EventTask(eventStr[0].trim(), dateTimeStr[0], dateTimeStr[1]);
            return new AddCommand(NewEventTask);

        } else if(arrOfStr[0].equals("delete")) {
            if(arrOfStr.length <= 1) {
                throw new RuntimeException("Specification of the DeleteTask is missing\n");
            }
            return new DeleteCommand(Integer.parseInt(arrOfStr[1]));

        } else {
            throw new RedException("Cannot understand input");
            }

    }
}
