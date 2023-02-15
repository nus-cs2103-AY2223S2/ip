package elise.commands;

import elise.EliseException;
import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;

/**
 * Command which finds specific task based on keyword.
 */
public class FindCommand implements Command {
    private final int code;
    private final String keyword;

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
    public String execute(Ui ui, TaskList taskList, Storage storage) throws EliseException {
        if (code == 0) {
            return ui.wrapText(taskList.find(keyword));
        } else {
            throw new EliseException("Invalid code.");
        }

    }
}
