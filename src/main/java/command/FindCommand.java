package command;

import storage.Storage;
import tasks.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.Arrays;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand object
     * @param keyword Keyword given by user to describe task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Returns Find command message
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Find command message
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.findMsg(keyword, taskList);
    }
}
