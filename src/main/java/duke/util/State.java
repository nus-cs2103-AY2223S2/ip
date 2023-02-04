package duke.util;

import java.util.HashMap;

/**
 * State encapsulates (modifications to) the program state
 *
 * @param doQuit Whether Duke should exit or not.
 * @see Stateful
 */
public record State(boolean doQuit, HashMap<String, String> aliases) {
    public State (boolean doQuit) {
        this(doQuit, new HashMap<>());
    }

    public State next(boolean doQuit) {
        return new State(doQuit, this.aliases);
    }
}
