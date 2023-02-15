package elise.commands;

import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;

// Command which finds specific task base on keyword.
public class FindCommand implements Command {
    private int code;
    private String keyword;

    /**
     * Constructor for Command of specified code and keyword.
     *
     * @param code Unique code for type of command.
     * @param keyword Keyword to match.
     */
    public FindCommand(int code, String keyword) {
        this.code = code;
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return Ui.wrapText(taskList.find(keyword));
    }
}
