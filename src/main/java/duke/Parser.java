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
        String firstWord;

        int indexOfFirstSpace = str.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            firstWord = str;
        } else {
            firstWord = str.substring(0, indexOfFirstSpace);
        }
        switch (firstWord) {
            case ("todo"):
                Parser.checkToDoCommand(str);
                command = new AddCommand(str);
                break;
            case ("deadline"):
                Parser.checkDeadlineCommand(str);
                command = new AddCommand(str);
                break;
            case ("event"):
                Parser.checkEventCommand(str);
                command = new AddCommand(str);
                break;
            case ("delete"):
                int deleteIndex = Parser.checkDeleteCommand(str);
                command = new DeleteCommand(deleteIndex);
                break;
            case ("mark"):
                int markIndex = Parser.checkMarkCommand(str);
                command = new MarkCommand(markIndex);
                break;
            case ("unmark"):
                int unMarkIndex = Parser.checkUnMarkCommand(str);
                command = new UnMarkCommand(unMarkIndex);
                break;
            case ("list"):
                Parser.checkListCommand(str);
                command = new ListCommand();
                break;
            case ("find"):
                String keyword = Parser.checkFindCommand(str);
                command = new FindCommand(keyword);
                break;
            case ("bye"):
                Parser.checkExitCommand(str);
                command = new ExitCommand();
                break;
            default:
                throw new RuntimeException("Huh? I don't know what that means :(");
        }
        return command;
    }

    public static void checkToDoCommand(String str) {
        if (str.equals("todo") || str.equals("todo ")) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
    }

    public static void checkDeadlineCommand(String str) {
        if (str.equals("deadline") || str.equals("deadline ")) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        final var target = " /by ";
        final var targetLength = 5;
        if (!str.contains(target)) {
            throw new RuntimeException("Unable to create Deadline! Deadline commands need a /by field!");
        }
        int index = str.indexOf(target);
        final var deadlineDescriptionStartIndex = 9;
        if (index <= deadlineDescriptionStartIndex) {
            throw new RuntimeException("Unable to create Deadline! "
                    + "Description for deadline cannot be left blank!");
        }
        String deadline = str.substring(index + targetLength);
        int dateTimeLength = deadline.length();
        final var minDateTimeLength = 13;
        final var maxDateTimeLength = 15;
        if (!(dateTimeLength >= minDateTimeLength && dateTimeLength <= maxDateTimeLength)) {
            throw new RuntimeException("Unable to create Deadline! "
                    + "Check your date and time. They have to be in the format of dd/mm/yyyy hhmm");
        }
        int firstSlash = deadline.indexOf("/");
        int secondSlash = deadline.indexOf("/", firstSlash + 1);
        if (firstSlash == -1 || secondSlash == -1) {
            throw new RuntimeException("Unable to create Deadline! "
                    + "Check your date format. Use / to separate day, month and year.");
        }
    }

    public static void checkEventCommand(String str) {
        if (str.equals("event") || str.equals("event ")) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        final String target1 = " /from ";
        final String target2 = " /to ";
        final var target1Length = target1.length();
        final var target2Length = target2.length();
        if (!(str.contains(target1) && str.contains(target2))) {
            throw new RuntimeException("Unable to create Event! Event commands need a /from and /to field!");
        }
        int index1 = str.indexOf(target1);
        int index2 = str.indexOf(target2);
        final var minCharBetweenTargets = 20;
        if (index2 - index1 < 0) {
            throw new RuntimeException("Unable to create event! "
                    + "The /from field has to be before the /to field.");
        } else if (index2 - index1 < minCharBetweenTargets) {
            throw new RuntimeException("Unable to create event! Please enter a valid /from field.");
        }
        final int eventDescriptionStartIndex = 6;
        if (index1 <= eventDescriptionStartIndex) {
            throw new RuntimeException("Unable to create event! Description for event cannot be left blank");
        }
        String start = str.substring(index1 + target1Length, index2);
        String end = str.substring(index2 + target2Length);
        int startFirstSlash = start.indexOf("/");
        int startSecondSlash = start.indexOf("/", startFirstSlash + 1);
        int endFirstSlash = end.indexOf("/");
        int endSecondSlash = end.indexOf("/", endFirstSlash + 1);
        if (startFirstSlash == -1 || startSecondSlash == -1 || endFirstSlash == -1 || endSecondSlash == -1) {
            throw new RuntimeException("Unable to create Deadline! Check your date and time format!");
        }
    }

    public static int checkDeleteCommand(String str) {
        try {
            if (str.equals("delete") || str.equals("delete ")) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var deleteStartIndex = 7;
            String deletedIndex = str.substring(deleteStartIndex);
            int index = Integer.parseInt(deletedIndex) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    public static int checkMarkCommand(String str) {
        try {
            if (str.equals("mark") || str.equals("mark ")) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var markStartIndex = 5;
            String markedIndex = str.substring(markStartIndex);
            int index = Integer.parseInt(markedIndex) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    public static int checkUnMarkCommand(String str) {
        try {
            if (str.equals("unmark") || str.equals("unmark ")) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var unMarkStartIndex = 7;
            String unmarkedIndex = str.substring(unMarkStartIndex);
            int index = Integer.parseInt(unmarkedIndex) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    public static void checkListCommand(String str) {
        if (!str.equals("list")) {
            throw new RuntimeException("List commands do not have a field!");
        }
    }

    public static String checkFindCommand(String str) {
        if (str.equals("find") || str.equals("find ")) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        final var findStartIndex = 5;
        String keyword = str.substring(findStartIndex);
        if (keyword.equals("")) {
            throw new RuntimeException("What would you like me to find?");
        }
        return keyword;
    }

    public static void checkExitCommand(String str) {
        if (!str.equals("bye")) {
            throw new RuntimeException("Exit commands do not have a field!");
        }
    }
}
