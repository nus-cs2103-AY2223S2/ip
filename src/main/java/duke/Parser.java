package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;

/**
 * Makes sense of the user's commands.
 */
public class Parser {
    /**
     * Returns corresponding command objects from user commands given in strings.
     * @param str User command
     * @return Command object corresponding to the user command.
     * @throws RuntimeException If user commands don't make sense.
     */
    public static Command parse(String str) {
        Command command;
        boolean todoCheck = str.startsWith("todo ");
        boolean deadlineCheck = str.startsWith("deadline ");
        boolean eventCheck = str.startsWith("event ");
        boolean deleteCheck = str.startsWith("delete ");
        boolean markCheck = str.startsWith("mark ");
        boolean unMarkCheck = str.startsWith("unmark ");
        boolean listCheck = str.equals("list");
        boolean findCheck = str.startsWith("find ");
        boolean exitCheck = str.equals("bye");
        boolean nothingCheck = str.equals("");
        if (str.equals("todo") || str.equals("todo ") || str.equals("deadline") || str.equals("event")
                || str.equals("delete") || str.equals("mark") || str.equals("unmark")
                || str.equals("find")) {
            throw new RuntimeException("This command's field cannot be left blank!");
        } else if (todoCheck || deadlineCheck || eventCheck) {
            if (deadlineCheck) {
                String target = " /by ";
                if (!str.contains(target)) {
                    throw new RuntimeException("Unable to create Deadline! Deadline commands need a /by field!");
                }
                int index = str.indexOf(target);
                String description = str.substring(0, index);
                if (description.equals("")) {
                    throw new RuntimeException("Unable to create Deadline! "
                            + "Description for deadline cannot be left blank!");
                }
                String deadline = str.substring(index + 5);
                int dateTimeLength = deadline.length();
                if (!(dateTimeLength > 12 && dateTimeLength < 16)) {
                    throw new RuntimeException("Unable to create Deadline! "
                            + "Check your date and time. They have to be in the format of dd/mm/yyyy hhmm");
                }
                int firstSlash = deadline.indexOf("/");
                int secondSlash = deadline.indexOf("/", firstSlash + 1);
                if (firstSlash == -1 || secondSlash == -1) {
                    throw new RuntimeException("Unable to create Deadline! "
                            + "Check your date format. Use / to separate day, month and year.");
                }
            } else if (eventCheck) {
                String target1 = " /from ";
                String target2 = " /to ";
                if (!(str.contains(target1) && str.contains(target2))) {
                    throw new RuntimeException("Unable to create Event! Event commands need a /from and /to field!");
                }
                int index1 = str.indexOf(target1);
                int index2 = str.indexOf(target2);
                if (index2 - index1 < 0) {
                    throw new RuntimeException("Unable to create event! "
                            + "The /from field has to be before the /to field.");
                } else if (index2 - index1 < 20) {
                    throw new RuntimeException("Unable to create event! Please enter a valid /from field.");
                }
                String description = str.substring(0, index1);
                if (description.equals("")) {
                    throw new RuntimeException("Unable to create event! Description for event cannot be left blank");
                }
                String start = str.substring(index1 + 7, index2);
                String end = str.substring(index2 + 5);
                int startFirstSlash = start.indexOf("/");
                int startSecondSlash = start.indexOf("/", startFirstSlash + 1);
                int endFirstSlash = end.indexOf("/");
                int endSecondSlash = end.indexOf("/", endFirstSlash + 1);
                if (startFirstSlash == -1 || startSecondSlash == -1 || endFirstSlash == -1 || endSecondSlash == -1) {
                    throw new RuntimeException("Unable to create Deadline! Check your date and time format!");
                }
            }
            command = new AddCommand(str);
        } else if (deleteCheck) {
            try {
                String deletedIndex = str.substring(7);
                int index = Integer.parseInt(deletedIndex) - 1;
                command = new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Target was not a number!");
            }
        } else if (markCheck) {
            try {
                String markedIndex = str.substring(5);
                int index = Integer.parseInt(markedIndex) - 1;
                command = new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Target was not a number!");
            }
        } else if (unMarkCheck) {
            try {
                String unmarkedIndex = str.substring(7);
                int index = Integer.parseInt(unmarkedIndex) - 1;
                command = new UnMarkCommand(index);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Target was not a number!");
            }
        } else if (listCheck) {
            command = new ListCommand();
        } else if (findCheck) {
            String keyword = str.substring(5);
            if (keyword.equals("")) {
                throw new RuntimeException("What would you like me to find?");
            }
            command = new FindCommand(keyword);
        } else if (exitCheck) {
            command = new ExitCommand();
        } else if (nothingCheck) {
            throw new RuntimeException("Please enter a command!");
        } else {
            throw new RuntimeException("Huh? I don't know what that means :(");
        }
        return command;
    }
}
