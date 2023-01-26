package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Ui;

public class CommandMark extends Command{
    private final int index;
    private final boolean mark;

    public CommandMark(String command, int index, boolean mark) {
        super(command);
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index, mark);
        if (mark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        taskList.getTask(index).setStatus(mark);
        System.out.printf(taskList.getTask(index).toString() + "\n");

        storage.writeArray(taskList);
    }
}
