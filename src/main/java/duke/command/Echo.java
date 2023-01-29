package duke.command;

import duke.task.TaskList;

/**
 * This class implements echo functionality, which repeat the command that user gave.
 */
public class Echo extends Commands {
    public Echo(String str) {
        this.setCommandStorage(str);
    }

    /**
     * This method simply prints the content of the user input stored in the command.
     * @param tasks
     */
    @Override
    public void execute(TaskList tasks) {
        System.out.println(this.getCommandStorage());
    }
}