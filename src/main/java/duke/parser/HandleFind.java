package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class HandleFind {
    public HandleFind() {
    }

    public static String performFind(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.trim().split(" ").length > 1;
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
