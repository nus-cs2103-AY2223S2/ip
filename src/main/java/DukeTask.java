/**
 * The abstraction behind the tasks stored by the Duke chat-bot.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public abstract class DukeTask {
    private boolean isDone = false;
    private final String value;

    /**
     * Provides a base constructor common to all task implementations that
     * stores a task and if it is done.
     *
     * @param val The task to be completed.
     */
    public DukeTask(String val) {
        this.value = val;
    }

    /**
     * Indicate that this task is done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Indicate that this task is not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s", (this.isDone ? "X": " "), this.value
        );
    }
}
