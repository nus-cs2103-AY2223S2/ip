package duke;

/**
 * Class that handles making sense of the user command
 */
public class Parser {
    /**
     * Parses user input and executes the specified commands
     *
     * @param userInput String of user input read from command line
     * @param taskList TaskList object to which tasks are added to or deleted from
     * @param fileManager Storage object that deals with loading tasks from file and saving tasks in file
     * @return String that is Duke's response to the user's input
     */
    public String parse(String userInput, TaskList taskList, Storage fileManager) {
        String result = "";
        String[] splitInput = userInput.split(" ", 2);
        switch (splitInput[0]) {
        case "bye":
            result += "Bye, hope to see you again soon!\nClick on the 'close window' icon to exit";
            Ui.exit();
            break;
        case "l":
        case "list":
            result += Duke.displayList();
            break;
        case "f":
        case "find":
            String keyword = splitInput[1];
            TaskList tempTaskList = taskList.findTasks(keyword);
            result += "Here are the matching tasks in your list:\n";
            result += tempTaskList.printList();
            break;
        case "m":
        case "mark":
            int markTaskNum = Integer.parseInt(splitInput[1]);
            assert markTaskNum < taskList.size() && markTaskNum > 0:
                    "index of task to mark as complete is out of range";
            result += taskList.getTask(markTaskNum - 1).setCompleted(true);
            break;
        case "um":
        case "unmark":
            int unmarkTaskNum = Integer.parseInt(splitInput[1]);
            assert unmarkTaskNum < taskList.size() && unmarkTaskNum > 0:
                    "index of task to unmark as complete is out of range";
            result += taskList.getTask(unmarkTaskNum - 1).setCompleted(false);
            break;
        case "d":
        case "deadline":
            if (userInput.contains("/by ")) {
                System.out.println(splitInput[1]);
                result += Duke.addToList(new Deadline(false, splitInput[1]));
            } else {
                try {
                    throw new DukeException("deadline");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "e":
        case "event":
            if (userInput.contains("/from ") && userInput.contains("/to ")) {
                System.out.println(splitInput[1]);
                result += Duke.addToList(new Event(false, splitInput[1]));
            } else {
                try {
                    throw new DukeException("event");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "t":
        case "todo":
            if (userInput.length() > 5) {
                System.out.println(splitInput[1]);
                result += Duke.addToList(new Todo(false, splitInput[1]));
            } else {
                try {
                    throw new DukeException("todo");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "del":
        case "delete":
            try {
                int num = Integer.parseInt(splitInput[1]);
                assert num < taskList.size() && num > 0: "index of task to delete is out of range";
                result += Duke.removeFromList(num);
            } catch (NumberFormatException ex) { // To handle non-int input
                ex.printStackTrace();
            }
            break;
        default:
            try {
                throw new DukeException();
            } catch (DukeException de) {
                result += de.toString();
            }
        }
        fileManager.writeToFile(taskList);
        return result;
    }

}
