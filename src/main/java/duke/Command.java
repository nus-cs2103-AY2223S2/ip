package duke;

import java.io.IOException;

/**
 * Abstraction of a command to be executed.
 * Stores all information necessary for execution.
 */
public class Command {
    /*
    Command codes:
    -1 - Invalid input.
    0 - Bye
    1 - list
    2 - mark
    3 - unmark
    4 - to_do
    5 - deadline
    6 - event
    7 - delete
     */

    private final int code;
    private int index;
    private String[] content;

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
     *
     * @param ui Ui object of Duke.
     * @param taskList TaskList object of Duke.
     * @param storage Storage object of Duke.
     * @throws DukeException DukeException unique to Duke
     * @throws IOException Unexpected IOException
     */

    protected void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException, IOException {
        if (code == -1) {
            return;
        }
        if (code == 0) {
            ui.byeMessage();
        } else if (code == 1) {
            taskList.list();
            ui.showLine();
        } else if (code == 2) {
            Task t = taskList.markDone(index);
            ui.markDoneMessage(t);
            storage.write();
        } else if (code == 3) {
            Task t = taskList.markUndone(index);
            ui.markUndoneMessage(t);
            storage.write();
        } else if (code == 4) {
            Task t = taskList.addTask(0, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 5) {
            Task t = taskList.addTask(1, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 6) {
            Task t = taskList.addTask(2, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 7) {
            Task t = taskList.delete(index);
            ui.deleteMessage(t, taskList);
            storage.write();
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
