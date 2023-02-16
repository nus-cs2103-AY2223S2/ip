package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 1500);
        this.cmdOutput = "Otsunakiri~ Byebye!~";
    }
}
