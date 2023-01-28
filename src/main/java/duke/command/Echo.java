package duke.command;

import duke.task.TaskList;

public class Echo extends Commands {
    public Echo(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(TaskList tasks) {
        System.out.println(this.getCommandStorage());
    }
}