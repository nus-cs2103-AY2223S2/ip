package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.DukeException;

/**
 * A command that asks Dukes to exit
 * @author Junyi
 */
public class ByeCommand extends Command {

    private ByeCommand() {}

    /**
     * Factory method to create bye command
     * @return An instance of ByeCommand.
     */
    public static ByeCommand createByeCommand() {
        return new ByeCommand();
    }

    @Override
    public String execute() throws DukeException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 500L);

        return "Till next time...";
    }

}
