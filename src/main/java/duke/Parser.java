package duke;

/**
 * The Parser class is to make sense of (interpret) the user commend.
 * Executes different functions based on the user commend/input.
 */
public class Parser {
    /**
     * Interprets the task from the history file.
     *
     * @param userInput Input the previous file history into the application.
     * @return All the tasks that stored previously in the file.
     */
    public static Task parseFile(String userInput) {
        String recordType = userInput.substring(0, 3);
        String recordStatus = userInput.substring(3, 6);
        String recordDescription = userInput.substring(7);
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
     * @return A boolean value that indicate whether there is
     *         change that need to be updated due to the task conducted.
     * @throws TaskNotExistException The task does not exist in the list.
     * @throws MissingNumberException Not indication of task index number.
     * @throws MissingDescriptionException No task description.
     */
    public static String parseInput(TaskList list, String userInput)
            throws TaskNotExistException, MissingNumberException, MissingDescriptionException, UnknownInputException {
        int chosenTask;

        switch (userInput.split("\\s+")[0]) {
        case "list":
            return list.printList();
        case "mark":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("mark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            return list.mark(chosenTask);
        case "unmark":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("unmark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            return list.unmark(chosenTask);
        case "todo":
            if (!userInput.contains(" ")) {
                throw new MissingDescriptionException("todo");
            }
            String todoDescription = userInput.substring(userInput.indexOf(" ")).trim();
            return list.todo(todoDescription);
        case "deadline":
            if (!userInput.contains(" ")) {
                throw new MissingDescriptionException("deadline");
            }
            if (!userInput.contains("/")) {
                throw new MissingDescriptionException("deadline");
            }
            String ddFull = userInput.substring(userInput.indexOf(" ")).trim();
            String ddDescription = ddFull.split("/")[0];
            String ddDate = ddFull.split("/")[1].substring(ddFull.split("/")[1]
                    .indexOf(" ")).trim();
            return list.deadline(ddDescription, new TimeConvertor(ddDate));
        case "event":
            if (!userInput.contains(" ")) {
                throw new MissingDescriptionException("event");
            }
            if (!userInput.contains("/")) {
                throw new MissingDescriptionException("event");
            }
            String eventFull = userInput.substring(userInput.indexOf(" ")).trim();
            String eventDescription = eventFull.split("/")[0];
            String eventFrom = eventFull.split("/")[1]
                    .substring(eventFull.split("/")[1].indexOf(" ")).trim();
            String eventTo = eventFull.split("/")[2].substring(eventFull
                    .split("/")[2].indexOf(" ")).trim();
            return list.event(eventDescription, new TimeConvertor(eventFrom), new TimeConvertor(eventTo));
        case "delete":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("delete");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            return list.delete(chosenTask);
        case "find":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("find");
            }
            return list.find(userInput.substring(userInput.indexOf(" ") + 1));
        case "check":
            if (!userInput.contains("/")) {
                throw new MissingNumberException("check");
            }
            String checkDeadline = userInput.split("/")[1];
            return list.check(checkDeadline);
        default:
            return "Oh no, I am not sure what that means, could you try again?";
        }
    }
}

