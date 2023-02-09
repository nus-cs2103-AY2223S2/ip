package kira.command;

import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for BYE.
 */
public class ByeCommand extends Command {

    /**
     * Constructs an executable to shut down the bot.
     */
    public ByeCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasklist) {
        ui.endMsg();
        sleepAndExit();
        return false;
    }

    private void sleepAndExit() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            Platform.exit();
        });
    }

}
