package duke.command;

import duke.task.TaskList;

/**
 * Displays the tasks in the current task list.
 */
public class List extends Commands{
    public List(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(TaskList tasks) {
        System.out.println("Here are the tasks in your list: \n");
        tasks.listTasks();
    }
}
