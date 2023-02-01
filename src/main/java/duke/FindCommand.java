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
    public void execute(TaskList taskList) throws DukeException {
        taskList.find(keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
