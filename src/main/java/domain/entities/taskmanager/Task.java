package domain.entities.taskmanager;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;
import domain.entities.core.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * This shall resemble a Task. The reason that no modifiers was added was
 * because this class shall be package private.
 */
public abstract class Task implements Serializable {
    /**
     * Creates a new task with the given tokens.
     * @param tokens the list of String to be processed.
     * @param delims the keywords after which we retrieve relevant data.
     * @throws InvalidArgumentException if the name is null.
     */
    public Task(String[] tokens, Set<String> delims) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a task " +
                    "should not be null", tokens);
        }
        this.name = tmp.getLeft();
        if (tmp.getRight().get(completeKey) != null) {
            if (tmp.getRight().get(completeKey).equals("true")) {
                this.isComplete = true;
            } else {
                this.isComplete = false;
            }
        } else {
            this.isComplete = false;
        }
    }

    /**
     * The name of the list item.
     * <p>
     * It is set to final now as we do not allow the change of names for now.
     */
    private final String name;

    /**
     * If the object is complete or not.
     */
    private boolean isComplete;

    /**
     * Sets the isComplete to complete.
     *
     * @param complete the value to set the isComplete to.
     */
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    /**
     * Toggles the isComplete value, i.e. if it were true, then set it to false;
     * if it were false, then set it to true.
     */
    public void toggleIsComplete() {
        isComplete = !isComplete;
    }

    /**
     * The key for identifying if the object is marked as complete or not.
     */
    protected static final String completeKey = "/complete";

    @Override
    public String serialize() {
        if (isComplete) {
            return name + " " + completeKey + " true";
        } else {
            return name;
        }
    }

    @Override
    public String toString() {
        String prefix;
        if (isComplete) {
            prefix = "[x] ";
        } else {
            prefix = "[ ] ";
        }
        return prefix + name;
    }
}
