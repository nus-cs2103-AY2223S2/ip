package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class HandleFind {
    public HandleFind() {
    }

    public static String performFind(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.split(" ").length > 1;

        if (!correctFormat) {
            throw new WrongFormatException("find 'Target word'");
        }

        String targetString = input.substring(5);
        List<Task> targetList = new ArrayList<>();
        for (Task t : tasklist.getTaskList()) {
            if (t.getTask().contains(targetString)) {
                targetList.add(t);
            }
        }

        if (targetList.isEmpty()) {
            return "Oops! :( There are no matching tasks found.";
        } else {
            String listFound = "Here are the matching tasks in your list:";
            for (int i = 1; i < targetList.size() + 1; i++) {
                listFound = listFound + "\n" + i + "." + (targetList.get(i - 1)).toString();
            }
            return listFound;
        }
    }
}
