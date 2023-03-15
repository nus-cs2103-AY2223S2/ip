package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showTasksWithKeyword(keyword, taskList);
    }

    @Override
    public String toString() {
        return "find";
    }
}
