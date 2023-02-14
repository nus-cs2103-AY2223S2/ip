package duke.command;

import duke.Tasklist;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Tasklist tasklist) throws Exception {
        String returnedString = tasklist.findTasks(this.keyword);
        return returnedString;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
