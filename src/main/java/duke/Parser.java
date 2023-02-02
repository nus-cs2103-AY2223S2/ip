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
    public static boolean parseInput(TaskList list, String userInput)
            throws TaskNotExistException, MissingNumberException, MissingDescriptionException, UnknownInputException {
        int chosenTask;

        switch (userInput.split("\\s+")[0]) {
        case "bye":
            list.bye();
            return false;
        case "list":
            list.printList();
            return false;
        case "mark":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("mark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.mark(chosenTask);
            return true;
        case "unmark":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("unmark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.unmark(chosenTask);
            return true;
        case "todo":
            if (!userInput.contains(" ")) {
                throw new MissingDescriptionException("todo");
            }
            String todoDescription = userInput.substring(userInput.indexOf(" ")).trim();
            list.todo(todoDescription);
            return true;
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
            list.deadline(ddDescription, new TimeConvertor(ddDate));
            return true;
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
            list.event(eventDescription, new TimeConvertor(eventFrom), new TimeConvertor(eventTo));
            return true;
        case "delete":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("delete");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.delete(chosenTask);
            return true;
        case "find":
            if (!userInput.contains(" ")) {
                throw new MissingNumberException("find");
            }
            list.find(userInput.substring(userInput.indexOf(" ") + 1));
            return false;
        case "check":
            if (!userInput.contains("/")) {
                throw new MissingNumberException("check");
            }
            String checkDeadline = userInput.split("/")[1];
            list.check(checkDeadline);
            return false;
        default:
            System.out.println("Oh no, I am not sure what that means, could you try again?");
            return false;
        }
    }
}

