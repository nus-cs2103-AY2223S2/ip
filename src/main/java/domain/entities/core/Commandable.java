package domain.entities.core;

/**
 * The interface for objects that can be executed.
 */
public interface Commandable {
    /**
     * Executes the executable.
     *
     * @param tokens the tokens, i.e. the arguments, for the executable. They
     *               should include the first word.
     * @return the exit status of the runnable, which decides what to do with
     *         the next executable in line.
     */
    ExitStatus execute(String[] tokens);

}
