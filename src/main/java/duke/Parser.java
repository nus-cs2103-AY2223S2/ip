package duke;

/**
 * The Parser class is to make sense of (interpret) the user commend.
 * Execute different function based on the user commend/input.
 */
public class Parser {
    /**
     * Interpret the task from the history file.
     *
     * @param userInput Input the previous file history into the
     *                  application.
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
            String byDate = recordDescription.substring(recordDescription.indexOf(":") + 2,
                    recordDescription.length() - 1);
            recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
            item = new Deadline(recordDescription, new TimeConvertor(byDate, "MMM dd yyyy hh:mma"));
            break;
        case "[E]":
            String fromDate = recordDescription.substring(recordDescription.indexOf("from:") + 6,
                    recordDescription.indexOf("to:") - 1);
            String toDate = recordDescription.substring(recordDescription.indexOf("to:") + 4,
                    recordDescription.length() - 1);
            recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
            item = new Event(recordDescription, new TimeConvertor(fromDate, "MMM dd yyyy hh:mma"),
                    new TimeConvertor(toDate, "MMM dd yyyy hh:mma"));
            break;
        }
        if (recordStatus.equals("[X]")) {
                item.setIsDone();
        }
        return item;
    }

    /**
     *
     * @param list Instantiate <code>TaskList</code> object.
     * @param userInput User input into the application.
     * @return a boolean value that indicate whether there is
     *         change that need to be updated due to the task conducted.
     * @throws TaskNotExistException The task does not exist in the list.
     * @throws MissingNumberException Not indication of task index number.
     * @throws MissingDescriptionException No task description.
     */
    public static boolean parseInput(TaskList list, String userInput)
            throws TaskNotExistException, MissingNumberException, MissingDescriptionException,
            UnknownInputException {
        int chosenTask;
        switch (userInput.split("\\s+")[0]) {
        case "bye":
            list.bye();
            return false;
        case "list":
            list.printList();
            return false;
        case "mark":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingNumberException("mark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.mark(chosenTask);
            return true;
        case "unmark":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingNumberException("unmark");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.unmark(chosenTask);
            return true;
        case "todo":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingDescriptionException("todo");
            }
            String todo_description = userInput.substring(userInput.indexOf(" ")).trim();
            list.todo(todo_description);
            return true;
        case "deadline":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingDescriptionException("deadline");
            }
            if (userInput.indexOf("/") == -1) {
                throw new MissingDescriptionException("deadline");
            }
            String dd_full = userInput.substring(userInput.indexOf(" ")).trim();
            String dd_description = dd_full.split("/")[0];
            String dd_date = dd_full.split("/")[1].substring(dd_full.split("/")[1]
                    .indexOf(" ")).trim();
            list.deadline(dd_description, new TimeConvertor(dd_date));
            return true;
        case "event":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingDescriptionException("event");
            }
            if (userInput.indexOf("/") == -1) {
                throw new MissingDescriptionException("event");
            }
            String event_full = userInput.substring(userInput.indexOf(" ")).trim();
            String event_description = event_full.split("/")[0];
            String event_from = event_full.split("/")[1]
                    .substring(event_full.split("/")[1].indexOf(" ")).trim();
            String event_to = event_full.split("/")[2].substring(event_full
                    .split("/")[2].indexOf(" ")).trim();
            list.event(event_description, new TimeConvertor(event_from),
                    new TimeConvertor(event_to));
            return true;
        case "delete":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingNumberException("delete");
            }
            chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
            list.delete(chosenTask);
            return true;
        case "find":
            if (userInput.indexOf(" ") == -1) {
                throw new MissingNumberException("find");
            }
            list.find(userInput.substring(userInput.indexOf(" ") + 1));
            return false;
        case "check":
            if (userInput.indexOf("/") == -1) {
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

