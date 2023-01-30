package chungus;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Represents an abstract, basic, I/O user interface.
 */
public interface Ui {
    /**
     * Starts the UI instance.
     * 
     * @param handler    A lambda to apply to each line of user input.
     * @param beforeEach Something to run before each response.
     * @param afterEach  Something to run after each response.
     * @param isRunning  Whether the app is still running.
     */
    public void startWith(Consumer<String> handler, Runnable beforeEach, Runnable afterEach, AtomicBoolean isRunning);

    /**
     * Displays text.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     */
    public void print(String msg, Object... args);

    /**
     * Displays text formatted for information.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     */
    public void info(String msg, Object... args);

    /**
     * Displays text formatted as an error.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     */
    public void error(String msg, Object... args);
}
