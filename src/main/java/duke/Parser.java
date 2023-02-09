package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.UpdateCommand;

/**
 * Makes sense of the user's commands.
 */
public class Parser {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String UPDATE = "update";
    private static final String BYE = "bye";

    /**
     * Returns corresponding command objects from user commands given in strings.
     * @param str User command
     * @return Command object corresponding to the user command.
     * @throws RuntimeException If user commands don't make sense.
     */
    public static Command parse(String str) {
        Command command;
        String firstWord;
        final int strLength = str.length();

        int indexOfFirstSpace = str.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            firstWord = str;
        } else {
            firstWord = str.substring(0, indexOfFirstSpace);
        }
        switch (firstWord) {
        case (Parser.TODO):
            Parser.checkToDoCommand(str);
            final var toDoFormatLength = Parser.TODO.length() + 1;
            assert strLength > toDoFormatLength;
            command = new AddCommand(str);
            break;
        case (Parser.DEADLINE):
            Parser.checkDeadlineCommand(str);
            final var deadlineFormatLength = 28;
            final var deadlineField = " /by ";
            assert strLength > deadlineFormatLength;
            assert str.contains(deadlineField);
            command = new AddCommand(str);
            break;
        case (Parser.EVENT):
            Parser.checkEventCommand(str);
            final var eventFormatLength = 44;
            final var eventField1 = " /from ";
            final var eventField2 = " /to ";
            assert strLength > eventFormatLength;
            assert str.contains(eventField1) && str.contains(eventField2);
            command = new AddCommand(str);
            break;
        case (Parser.DELETE):
            int deleteIndex = Parser.getDeleteIndex(str);
            assert !(deleteIndex < TaskList.MIN_INDEX && deleteIndex > TaskList.MAX_INDEX);
            command = new DeleteCommand(deleteIndex);
            break;
        case (Parser.MARK):
            int markIndex = Parser.getMarkIndex(str);
            assert !(markIndex < TaskList.MIN_INDEX && markIndex > TaskList.MAX_INDEX);
            command = new MarkCommand(markIndex);
            break;
        case (Parser.UNMARK):
            int unMarkIndex = Parser.getUnMarkIndex(str);
            assert !(unMarkIndex < TaskList.MIN_INDEX && unMarkIndex > TaskList.MAX_INDEX);
            command = new UnMarkCommand(unMarkIndex);
            break;
        case (Parser.LIST):
            Parser.checkListCommand(str);
            command = new ListCommand();
            break;
        case (Parser.FIND):
            String keyword = Parser.getKeyword(str);
            command = new FindCommand(keyword);
            break;
        case (Parser.UPDATE):
            int updateIndex = Parser.getUpdateIndex(str);
            String body = Parser.getUpdateBody(str);
            command = new UpdateCommand(updateIndex, body);
            break;
        case (Parser.BYE):
            Parser.checkExitCommand(str);
            command = new ExitCommand();
            break;
        default:
            throw new RuntimeException("Huh? I don't know what that means :(");
        }
        return command;
    }

    private static void checkToDoCommand(String str) {
        String toDoSpace = Parser.TODO + " ";
        if (str.equals(Parser.TODO) || str.equals(toDoSpace)) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
    }

    private static void checkDeadlineCommand(String str) {
        String deadlineSpace = Parser.DEADLINE + " ";
        if (str.equals(Parser.DEADLINE) || str.equals(deadlineSpace)) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        final var target = " /by ";
        final var targetLength = target.length();
        if (!str.contains(target)) {
            throw new RuntimeException("Unable to create Deadline! Deadline commands need a /by field!");
        }
        int index = str.indexOf(target);
        final var deadlineDescriptionStartIndex = Parser.DEADLINE.length() + 1;
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

    private static void checkEventCommand(String str) {
        String eventSpace = Parser.EVENT + " ";
        if (str.equals(Parser.EVENT) || str.equals(eventSpace)) {
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
        boolean isTarget1BehindTarget2 = index2 - index1 < 0;
        if (isTarget1BehindTarget2) {
            throw new RuntimeException("Unable to create event! "
                    + "The /from field has to be before the /to field.");
        } else if (index2 - index1 < minCharBetweenTargets) {
            throw new RuntimeException("Unable to create event! Please enter a valid /from field.");
        }
        final int eventDescriptionStartIndex = Parser.EVENT.length() + 1;
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

    private static int getDeleteIndex(String str) {
        try {
            String deleteSpace = Parser.DELETE + " ";
            if (str.equals(Parser.DELETE) || str.equals(deleteSpace)) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var deleteStartIndex = Parser.DELETE.length() + 1;
            String deletedIndex = str.substring(deleteStartIndex);
            int index = Integer.parseInt(deletedIndex) - 1;
            if (index < TaskList.MIN_INDEX || index > TaskList.MAX_INDEX) {
                throw new RuntimeException("Task does not exist!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    private static int getMarkIndex(String str) {
        try {
            String markSpace = Parser.MARK + " ";
            if (str.equals(Parser.MARK) || str.equals(markSpace)) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var markStartIndex = Parser.MARK.length() + 1;
            String markedIndex = str.substring(markStartIndex);
            int index = Integer.parseInt(markedIndex) - 1;
            if (index < TaskList.MIN_INDEX || index > TaskList.MAX_INDEX) {
                throw new RuntimeException("Task does not exist!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    private static int getUnMarkIndex(String str) {
        try {
            String unMarkSpace = Parser.UNMARK + " ";
            if (str.equals(Parser.UNMARK) || str.equals(unMarkSpace)) {
                throw new RuntimeException("This command's field can't be left blank!");
            }
            final var unMarkStartIndex = Parser.UNMARK.length() + 1;
            String unmarkedIndex = str.substring(unMarkStartIndex);
            int index = Integer.parseInt(unmarkedIndex) - 1;
            if (index < TaskList.MIN_INDEX || index > TaskList.MAX_INDEX) {
                throw new RuntimeException("Task does not exist!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    private static void checkListCommand(String str) {
        if (!str.equals(Parser.LIST)) {
            throw new RuntimeException("List commands do not have a field!");
        }
    }

    private static String getKeyword(String str) {
        String findSpace = Parser.FIND + " ";
        if (str.equals(Parser.FIND) || str.equals(findSpace)) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        final var findStartIndex = Parser.FIND.length() + 1;
        String keyword = str.substring(findStartIndex);
        if (keyword.equals("")) {
            throw new RuntimeException("What would you like me to find?");
        }
        return keyword;
    }

    private static int getUpdateIndex(String str) {
        String updateSpace = Parser.UPDATE + " ";
        if (str.equals(Parser.UPDATE) || str.equals(updateSpace)) {
            throw new RuntimeException("This command's field can't be left blank!");
        }
        int updateStartIndex = updateSpace.length();
        try {
            int secondSpaceIndex = str.indexOf(" ", updateStartIndex);
            if (secondSpaceIndex == -1) {
                throw new RuntimeException("Please follow the update command format!");
            }
            String updateIndexString = str.substring(updateStartIndex, secondSpaceIndex);
            int updateIndex = Integer.parseInt(updateIndexString) - 1;
            if (updateIndex < TaskList.MIN_INDEX || updateIndex > TaskList.MAX_INDEX) {
                throw new RuntimeException("Task does not exist!");
            }
            return updateIndex;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Target was not a number!");
        }
    }

    private static String getUpdateBody(String str) {
        int strLength = str.length();
        int updateStartIndex = Parser.UPDATE.length() + 1;
        int secondSpaceIndex = str.indexOf(" ", updateStartIndex);
        if (strLength <= secondSpaceIndex + 1) {
            throw new RuntimeException("Please enter what you want to update!");
        }
        String body = str.substring(secondSpaceIndex + 1);
        return body;
    }
    private static void checkExitCommand(String str) {
        if (!str.equals(Parser.BYE)) {
            throw new RuntimeException("Exit commands do not have a field!");
        }
    }
}
