package duke.command;

import duke.Storage;
import duke.TaskList;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class ByeCommand extends Command {
    private static final int MILLISECONDS_TO_SECONDS = 1000;
    public static final int CLOSING_DELAY = (int) (1.5 * MILLISECONDS_TO_SECONDS);

    public ByeCommand() {
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, CLOSING_DELAY);
        this.cmdOutput = "Otsunakiri~ Byebye!~";
    }
}
