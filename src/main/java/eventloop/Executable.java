package eventloop;

/**
 * The interface for objects that can be run.
 */
public interface Executable {
    /**
     * Runs the runnable.
     * @param tokens the tokens, i.e. the arguments, for the runnable. They
     *               should include the first word.
     * @return the exit status of the runnable, which decides what to do with
     * the next runnable in line.
     */
    ExitStatus execute(String[] tokens);
}
