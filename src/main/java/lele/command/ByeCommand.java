package lele.command;

import lele.Main;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

/**
 * For handling the actions to take
 * when exiting the application.
 */
public class ByeCommand extends Command {

    /**
     * For executing the actions taken for exiting
     * the app.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @return Output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Main.closeApp();
        return ui.printBye();
    }

    /**
     * To close the loop in the program.
     *
     * @return true, so that program terminates.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
