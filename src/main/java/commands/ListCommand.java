package commands;

/**
 * Represents a <code>Command</code> that when executed lists all the task in the task list.
 */
public class ListCommand extends Command{

    @Override
    public String execute(){
        return toResultString();
    }

    @Override
    String toResultString() {
        String opening = "Here are the tasks you have at hand: \n";
        assert taskList != null : "TaskList should not be null when listing tasks";
        String subject = taskList.getPrintableTasks();

        return opening + subject;
    }
}
