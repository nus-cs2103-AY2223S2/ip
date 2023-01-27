package types;

/**
 * An abstraction of commands or exception catchers
 * that requires some action to certain strings.
 */
public interface IHandler {
    /**
     * Takes given string and do the job accordingly.
     * @param s String to take.
     * @return Output string to feed the user back.
     */
    String take(String s);

    /**
     * Checks if this handler takes given string.
     * @param s String to check.
     * @return True if it takes, false otherwise.
     */
    boolean canTake(String s);
}
