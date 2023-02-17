package duke;

import javafx.application.Platform;

/**
 * The Parser class is to make sense of (interpret) the user commend.
 * Executes different functions based on the user commend/input.
 */
public class Parser {
    private static String getSubstring(String type, String userInput) {
        if (type.equals("recordType")) {
            return userInput.substring(0, 3);
        } else if (type.equals("recordStatus")) {
            return userInput.substring(3, 6);
        } else {
            return userInput.substring(7);
        }
    }

    /**
     * Interprets the task from the history file.
     *
     * @param userInput Input the previous file history into the application.
     * @return All the tasks that stored previously in the file.
     */
    public static Task parseFile(String userInput) {
        String recordType = getSubstring("recordType", userInput);
        String recordStatus = getSubstring("recordStatus", userInput);
        String recordDescription = getSubstring("recordDescription", userInput);
        Task item = null;

        switch (recordType) {
        case "[T]":
            item = new Todo(recordDescription);
            break;
        case "[D]":
            String byDate = recordDescription.substring(recordDescription.indexOf(":")
                    + 2, recordDescription.length() - 1);
            recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
            item = new Deadline(recordDescription, new TimeConvertor(byDate, "MMM dd yyyy hh:mma"));
            break;
        case "[E]":
            String fromDate = recordDescription.substring(recordDescription.indexOf("from:")
                    + 6, recordDescription.indexOf("to:") - 1);
            String toDate = recordDescription.substring(recordDescription.indexOf("to:")
                    + 4, recordDescription.length() - 1);
            recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
            item = new Event(recordDescription, new TimeConvertor(fromDate, "MMM dd yyyy hh:mma"),
                    new TimeConvertor(toDate, "MMM dd yyyy hh:mma"));
            break;
        default:
            assert false : "This type of record cannot be parsed";
        }

        if (recordStatus.equals("[X]") && item != null) {
            item.setIsDone();
        }

        return item;
    }

    /**
     * Parses the user input to interpret the user intended behaviours and take action.
     *
     * @param list Instantiate <code>TaskList</code> object.
     * @param userInput User input into the application.
     * @return A string value for the output.
     * @throws TaskNotExistException If task does not exist in the list.
     * @throws MissingNumberException If not indication of task index number.
     * @throws MissingDescriptionException If no task description.
     * @throws CheckNotFindException If no result find when check for the user input.
     * @throws NoSortTypeException If the type chosen cannot be sort.
     */
    public static String parseInput(TaskList list, String userInput)
            throws TaskNotExistException, MissingNumberException, MissingDescriptionException, CheckNotFindException,
            NoSortTypeException {
        switch (userInput.split("\\s+")[0].toLowerCase()) {
        case "bye":
            Platform.exit();
            return "";
        case "list":
            return list.printList();
        case "mark":
            return list.mark(userInput);
        case "unmark":
            return list.unmark(userInput);
        case "todo":
            return list.todo(userInput);
        case "deadline":
            return list.deadline(userInput);
        case "event":
            return list.event(userInput);
        case "delete":
            return list.delete(userInput);
        case "find":
            return list.find(userInput);
        case "check":
            return list.check(userInput);
        case "sort":
            return list.sort(userInput);
        default:
            assert false : "User input unknown case";
        }
        return "Oh no, I am not sure what that means, could you try again?";
    }
}

