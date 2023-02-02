package elise;

import java.io.IOException;

/**
 * Abstraction of a command to be executed.
 * Stores all information necessary for execution.
 */
public class Command {
    /*
    Command codes:
    -1 - Invalid input.
    0 - bye
    1 - list
    2 - mark
    3 - unmark
    4 - to_do
    5 - deadline
    6 - event
    7 - delete
    8 - find
     */

    private final int code;
    private int index;
    private String[] content;
    private String keyword;

    /**
     * Constructor for Command of specified code.
     *
     * @param code unique code for type of command.
     */
    protected Command(int code) {
        this.code = code;
    }

    /**
     * Constructor for Command of specified code and index.
     *
     * @param code unique code for type of command.
     * @param index index of list to perform action.
     */
    protected Command(int code, int index) {
        this.code = code;
        this.index = index;
    }

    /**
     * Constructor for Command of specified code and message.
     *
     * @param code unique code for type of command.
     * @param content String array of content for command.
     */
    protected Command(int code, String[] content) {
        this.code = code;
        this.content = content;
    }

    /**
     * Constructor for Command of specified code and keyword.
     *
     * @param code Unique code for type of command.
     * @param keyword Keyword to match.
     */
    protected Command(int code, String keyword) {
        this.code = code;
        this.keyword = keyword;
    }

    /**
     * Executes command.
     *
     * @param ui Ui object of Elise.
     * @param taskList TaskList object of Elise.
     * @param storage Storage object of Elise.
     * @throws EliseException EliseException unique to Elise
     * @throws IOException Unexpected IOException
     */
    protected String execute(Ui ui, TaskList taskList, Storage storage) throws EliseException, IOException {
        if (code == -1) {
            return "Bye";
        }
        if (code == 0) {
            return ui.byeMessage();
        } else if (code == 1) {
            return Ui.wrapText(taskList.list());
        } else if (code == 2) {
            Task t = taskList.markDone(index);
            storage.write();
            return ui.markDoneMessage(t);
        } else if (code == 3) {
            Task t = taskList.markUndone(index);
            storage.write();
            return ui.markUndoneMessage(t);
        } else if (code == 4) {
            Task t = taskList.addTask(0, content);
            storage.write();
            return ui.addMessage(t, taskList);
        } else if (code == 5) {
            Task t = taskList.addTask(1, content);
            storage.write();
            return ui.addMessage(t, taskList);
        } else if (code == 6) {
            Task t = taskList.addTask(2, content);
            storage.write();
            return ui.addMessage(t, taskList);
        } else if (code == 7) {
            Task t = taskList.delete(index);
            storage.write();
            return ui.deleteMessage(t, taskList);
        } else if (code == 8) {
            return Ui.wrapText(taskList.find(keyword));
        } else if (code == 9) {
            return ui.showHelp();
        } else {
            throw new EliseException("Invalid code.");
        }
    }

    /**
     * Returns true if exit command, false otherwise.
     *
     * @return Whether it is an exit command.
     */
    protected boolean isExit() {
        return code == 0;
    }


}
