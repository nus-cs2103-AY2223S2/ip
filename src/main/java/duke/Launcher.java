package duke;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.client.Main;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    private static ScheduledExecutorService scheduledExecutor;
    private static final int EXIT_DELAY = 2;
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Executes exiting of the application
     */
    public static void exit() {
        Platform.exit();
        scheduledExecutor.shutdown();
    }

    /**
     * Schedules the exit of the application with a delay
     */
    public static void scheduleExit() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(Launcher::exit, EXIT_DELAY, TimeUnit.SECONDS);
    }
}
