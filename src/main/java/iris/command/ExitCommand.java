package iris.command;

import java.util.Timer;
import java.util.TimerTask;

import iris.Main;
import iris.TaskList;
import iris.TaskStore;

/**
 * closes the chat
 */
public class ExitCommand extends Command {
    private static final String EXIT_TEXT = "Bye! Hope to see you soon!";

    class TimedExit extends TimerTask {
        public void run() {
            Main.quit();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) {
        Timer timer = new Timer();
        TimerTask task = new TimedExit();
        timer.schedule(task, 1000);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        return EXIT_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
