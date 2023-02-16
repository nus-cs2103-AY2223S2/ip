package commands;

/**
 * Represents a <code>Command</code> that when executed finds a task containing a given keyword.
 */
public class FindCommand extends Command{
    private String keyword;

    /**
     * Constructs a <code>FindCommand</code> for a particular keyword.
     * @param keyword the tasks containing this keyword is found by the command when executed.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        return toResultString();

    }

    @Override
    String toResultString() {
        String opening = "Here are the tasks that I have found with the keyword" + this.keyword + "\n";
        assert taskList != null : "TaskList should not be null when finding keyword";
        String subject = taskList.find(keyword).getPrintableTasks();

        return opening  + subject;
    }
}
