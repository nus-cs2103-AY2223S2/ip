package duke;

/**
 * Command class for find
 */
public class FindCommand implements Command {
    private String keywords;
    FindCommand(String s) {
        keywords = s;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.find(keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
