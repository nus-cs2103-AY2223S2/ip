package duke.command;

import duke.task.TaskList;

public class ListCommand {
    public static String executeList(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.listTasks();
    }
}
