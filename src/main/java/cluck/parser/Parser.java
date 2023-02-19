package cluck.parser;

import cluck.commands.*;
import cluck.exceptions.IncorrectArgumentException;
import cluck.exceptions.MissingArgumentException;
import cluck.messages.Messages;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

/**
 * Parser class takes user input and parses it into commands
 * that can be executed by Cluck in other packages.
 */
public class Parser {
    private static final String MAKE_DEADLINE = "deadline";
    private static final String MAKE_TODO = "todo";
    private static final String MAKE_EVENT = "event";
    private static final String DUE_DATE_FLAG = "/by ";
    private static final String EVENT_START_FLAG = "/from ";
    private static final String EVENT_END_FLAG = "/to ";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_TASK_COMMAND = "mark";
    private static final String UNMARK_TASK_COMMAND = "unmark";

    /**
     * Returns true if input is a number in string format, false otherwise.
     *
     * @param strNum String of interest.
     * @return boolean value
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Takes the string input of the user and converts it into an executable command.
     *
     * @return subtype of Command interface
     */
    public static Command commandFactory(String userInput) throws MissingArgumentException, IncorrectArgumentException {
        String[] words = userInput.split(" ");
        int taskIndex;

        switch (words[0]) {
        case EXIT_COMMAND:
            return new ExitCommand();

        case LIST_COMMAND:
            return new ListCommand();

        case MARK_TASK_COMMAND:
            if (words.length == 1) {
                throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
            }
            if (!isNumeric(words[1])) {
                throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
            }
            taskIndex = Integer.parseInt(words[1]);
            return new MarkTaskCommand(taskIndex - 1);

        case UNMARK_TASK_COMMAND:
            if (words.length == 1) {
                throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
            }
            if (!isNumeric(words[1])) {
                throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
            }
            taskIndex = Integer.parseInt(words[1]);
            return new UnmarkTaskCommand(taskIndex - 1);

        case MAKE_TODO:
            if (words.length < 2) {
                throw new MissingArgumentException(Messages.MESSAGE_DESCRIPTION_MISSING);
            }
            return new ToDoCommand(userInput.substring(5));

        case MAKE_DEADLINE:
            String body = userInput.substring(9);
            if (body.contains(DUE_DATE_FLAG)) {
                String[] fields = body.split(" " + DUE_DATE_FLAG);
                String description = fields[0];
                String dueDate = fields[1];
                Task currDeadline = new Deadline(description, dueDate);
                toDoList.add(currDeadline);
                System.out.println(Messages.MESSAGE_DEADLINE_ADDED + currDeadline.toString());
                System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                break;
            }
            System.out.println("    You're missing the '/by' flag, bucko!");
            break;

        case MAKE_EVENT:
            String substring = input.substring(6);
            if (substring.contains(EVENT_START_FLAG) && substring.contains(EVENT_END_FLAG)) {
                String[] fields = substring.split("\\s/\\w{2,4}\\s");
                Task currEvent = new Event(fields[0], fields[1], fields[2]);
                toDoList.add(currEvent);
                System.out.println(Messages.MESSAGE_EVENT_ADDED + currEvent.toString());
                System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                break;
            }
            System.out.println("    You're missing the either the '/from' or '/to' flag, or both! Buhcock!");
            break;

        case "delete":
            if (words.length == 1) {
                System.out.println(Messages.MESSAGE_INDEX_MISSING);
            } else if (isNumeric(words[1])) {
                Integer itemNumber = Integer.parseInt(words[1]);
                if (itemNumber > toDoList.size() || itemNumber <= 0) {
                    System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                } else {
                    System.out.println(Messages.MESSAGE_DELETE_SUCCESSFUL + "\n"
                            + toDoList.get(itemNumber - 1).toString());
                }
            } else {
                System.out.println(Messages.MESSAGE_INDEX_INVALID);
            }
            break;

        default:
            System.out.println(Messages.MESSAGE_INVALID_COMMAND);
    }
}
