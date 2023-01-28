package duke.command;

import duke.task.TaskList;

public class List extends Commands{
    public List(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(TaskList tasks) {
        tasks.listTasks();
    }
}
