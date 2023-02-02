package command;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;
import javafx.application.Platform;
import tasks.TaskList;



import java.util.Timer;
import java.util.TimerTask;

/***
 * This class has to do with exiting the program
 */

public class ByeCommand extends Command {

    /**
     * constructor for command.ByeCommand
     */
    public ByeCommand() {
        super();
    }

    /***
     * Exits the program
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     * // reused from github: https://github.com/nus-cs2103-AY2223S1/forum/issues/287
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            setExitTrue();
            return ui.exit();
        } finally {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 2000);
        }
    }
}
