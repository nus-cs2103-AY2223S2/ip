package duke.util;

import java.util.HashMap;

/**
 * State encapsulates (modifications to) the program state
 *
 * @see Stateful
 */
public class State {

    private final boolean doQuit;
    private final HashMap<String, String> aliases;

    public State(boolean doQuit, HashMap<String, String> aliases) {
        this.doQuit = doQuit;
        this.aliases = aliases;
    }

    public State (boolean doQuit) {
        this(doQuit, new HashMap<>());
    }

    public State next(boolean doQuit) {
        return new State(doQuit, this.aliases);
    }

    public boolean isDoQuit() {
        return doQuit;
    }

    public HashMap<String, String> getAliases() {
        return aliases;
    }
}
