package duke.util;

/**
 * State encapsulates (modifications to) the program state
 *
 * @param doQuit Whether Duke should exit or not.
 * @see Stateful
 */
public record State(boolean doQuit) {}
