package duke.parser;
import java.util.ArrayList;
import java.util.List;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the find command
 */
public class HandleFind {
    public HandleFind() {
    }

    /**
     * Check whether the input line to find matching tasks that contain target word is in the correct format or not
     * Perform the searching of target word in task list if input is in correct format
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, printing the matching tasks that contain the target word
     * @throws WrongFormatException This exception is thrown when input is not in correct format
     */
    public static String performFind(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.split(" ").length > 1;
        if (!correctFormat) {
            throw new WrongFormatException("find <Target word>");
        }

        String targetString = input.substring(5);
        List<Task> targetList = new ArrayList<>();
        for (Task t : tasklist.getTaskList()) {
            if (t.getTask().contains(targetString)) {
                targetList.add(t);
            }
        }
        if (targetList.isEmpty()) {
            return ui.showFindResult(false, "");
        } else {
            String listFound = "Here are the matching tasks in your list:";
            for (int i = 1; i < targetList.size() + 1; i++) {
                listFound = listFound + "\n" + i + "." + (targetList.get(i - 1)).toString();
            }
            return ui.showFindResult(true, listFound);
        }
    }
}
