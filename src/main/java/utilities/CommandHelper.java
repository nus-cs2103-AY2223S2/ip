package utilities;

import java.util.List;
import java.util.stream.Collectors;

import services.SpeakerRegistry;
import types.IHandler;


/**
 * A helper class to deal with Handlers.
 */
public final class CommandHelper {

    /**
     * Iterates through a given handler list to find
     * one that takes the string given.
     * Once found, returns true after execution.
     * @param sr SpeakerRegistry to broadcast the result with.
     * @param handlers The list to check.
     * @param expr The string to check.
     * @return Whether any handlers in the list is suitable.
     */
    public static boolean checkAndRun(SpeakerRegistry sr, List<IHandler> handlers, String expr) {
        List<String> a = handlers.parallelStream()
                .filter(c -> c.canTake(expr))
                .map(c -> c.take(expr)).collect(Collectors.toList());
        a.forEach(sr::broadcast);

        return !a.isEmpty();
    }

    /**
     * Checks if a given handler takes the string provided.
     * @param sr SpeakerRegistry to broadcast the result with.
     * @param c The handler to check.
     * @param expr The string to check.
     * @return Whether the handler is suitable.
     */
    public static boolean checkAndRun(SpeakerRegistry sr, IHandler c, String expr) {
        if (!c.canTake(expr)) {
            return false;
        }

        sr.broadcast(c.take(expr));
        return true;
    }
}
