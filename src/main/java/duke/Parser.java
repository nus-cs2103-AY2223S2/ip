package duke;

/**
 * Class that deals with making sense of the user command
 */
public class Parser {
    /**
     * Makes sense of the user command and calls corresponding methods
     *
     * @param userInput String of user input read from command line
     * @param taskList TaskList object to which tasks are added to or deleted from
     * @param fileManager Storage object that deals with loading tasks from file and saving tasks in file
     * @return String that is Duke's response to the user's input
     */
    public String parse(String userInput, TaskList taskList, Storage fileManager) {
        String result = "";
        String[] splitInput = userInput.split(" ");
        switch (splitInput[0]) {
        case "bye":
            result += "Bye, hope to see you again soon!\nClick on the 'close window' icon to exit";
            Ui.exit();
            break;
        case "list":
            result += Duke.displayList();
            break;
        case "find":
            String keyword = userInput.substring(5);
            TaskList tempTaskList = taskList.findTasks(keyword);
            result += "Here are the matching tasks in your list:\n";
            result += tempTaskList.printList();
            break;
        case "mark":
            int markTaskNum = Integer.parseInt(userInput.substring(5));
            result += taskList.getTask(markTaskNum - 1).setCompleted(true);
            break;
        case "unmark":
            int unmarkTaskNum = Integer.parseInt(userInput.substring(7));
            result += taskList.getTask(unmarkTaskNum - 1).setCompleted(false);
            break;
        case "deadline":
            if (userInput.contains("/by ")) {
                result += Duke.addToList(new Deadline(false, userInput));
            } else {
                try {
                    throw new DukeException("deadline");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "event":
            if (userInput.contains("/from ") && userInput.contains("/to ")) {
                result += Duke.addToList(new Event(false, userInput));
            } else {
                try {
                    throw new DukeException("event");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "todo":
            if (userInput.length() > 5) {
                result += Duke.addToList(new Todo(false, userInput));
            } else {
                try {
                    throw new DukeException("todo");
                } catch (DukeException de) {
                    result += de.toString();
                }
            }
            break;
        case "delete":
            try {
                int num = Integer.parseInt(userInput.substring(7));
                result += Duke.removeFromList(num);
            } catch (NumberFormatException ex) { // To handle non-int input
                ex.printStackTrace();
            }
            break;
        }
        fileManager.writeToFile(taskList);
        return result;
    }

}
