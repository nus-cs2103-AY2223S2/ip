package event_loop;

/**
 * This shall serve as the base for implementing an event loop. An event loop
 * loops forever unless the executable in it returns ExitStatus.terminate.
 */
public abstract class EventLoop {
    public EventLoop(Executable rootExecutable) {
        this.rootExecutable = rootExecutable;
    }

    /**
     * The root executable that shall be executed in the loop.
     */
    private final Executable rootExecutable;

    /**
     * The function for getting the tokens for each loop iteration.
     * @return the array of tokens.
     */
    abstract protected String[] getTokens();

    /**
     * The function that starts the event loop.
     */
    public void run() {
        ExitStatus status = ExitStatus.continueExecute;
        while (true) {
            status = rootExecutable.execute(getTokens());
            if (status == ExitStatus.terminate) {
                break;
            }
        }
    }
}
