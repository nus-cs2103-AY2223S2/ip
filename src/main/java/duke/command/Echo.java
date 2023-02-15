package duke.command;
import duke.task.TaskList;
import duke.UI.TextOutput;
/**
 * Implements echo functionality, which repeat the command that user gave.
 */
public class Echo extends Command {
    public Echo(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Prints the content of the user input stored in the command.
     * @param tasks List of tasks relevant to this command.
     */
    @Override
    public String execute(TaskList tasks) {
        return TextOutput.makeEchoString(tasks.toString());
    }
}