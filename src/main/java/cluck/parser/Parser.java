package cluck.parser;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.commands.ListCommand;
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

    private static final String SAVE_DIR_STRING = "SavedData";
    private static final String SAVE_FILE_STRING = "CluckSave.txt";

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
     * @return child of Command
     */
    public static Command commandFactory(String userInput) {
        String[] words = userInput.split(" ");

        switch (words[0]) {
            case EXIT_COMMAND:
                return new ExitCommand();

            case LIST_COMMAND:
                return new ListCommand();

            case "mark":
                if (words.length == 1) {
                    System.out.println(Messages.MESSAGE_INDEX_MISSING);
                } else if (isNumeric(words[1])) {
                    Integer itemNumber = Integer.parseInt(words[1]);
                    if (itemNumber > toDoList.size() || itemNumber <= 0) {
                        System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                    } else {
                        toDoList.get(itemNumber - 1).mark();
                        System.out.println(Messages.MESSAGE_MARK_SUCCESSFUL
                                + toDoList.get(itemNumber - 1).toString());
                    }
                } else {
                    System.out.println(Messages.MESSAGE_INDEX_INVALID);
                }
                break;

            case "unmark":
                if (words.length == 1) {
                    System.out.println(Messages.MESSAGE_INDEX_MISSING);
                } else if (isNumeric(words[1])) {
                    Integer itemNumber = Integer.parseInt(words[1]);
                    if (itemNumber > toDoList.size() || itemNumber <= 0) {
                        System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                    } else {
                        toDoList.get(itemNumber - 1).unmark();
                        System.out.println(Messages.MESSAGE_UNMARK_SUCCESSFUL
                                + toDoList.get(itemNumber - 1).toString());
                    }
                } else {
                    System.out.println(Messages.MESSAGE_INDEX_INVALID);
                }
                break;

            case MAKE_TODO:
                Task newTodo = new ToDo(input.substring(5));
                toDoList.add(newTodo);
                System.out.println(Messages.MESSAGE_TODO_ADDED + "\n" + newTodo.toString());
                System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                break;

            case MAKE_DEADLINE:
                String body = input.substring(9);
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
