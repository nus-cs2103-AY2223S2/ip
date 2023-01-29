package duke.command;
import duke.task.TaskList;

/**
 * Implements echo functionality, which repeat the command that user gave.
 */
public class Echo extends Commands {
    public Echo(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Prints the content of the user input stored in the command.
     * @param tasks List of tasks relevant to this command.
     */
    @Override
    public void execute(TaskList tasks) {
        System.out.println(this.getCommandStorage());
    }
}