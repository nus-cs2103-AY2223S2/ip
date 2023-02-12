package domain.entities.taskmanager;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import core.exceptions.InvalidArgumentException;
import core.singletons.Singletons;
import core.utils.Pair;
import core.utils.TokenUtilities;
import domain.entities.core.Serializable;

/**
 * This shall resemble a Task. The reason that no modifiers was added was
 * because this class shall be package private.
 */
public abstract class Task implements Serializable, Comparable<Task> {
    /**
     * The key for identifying if the object is marked as complete or not.
     */
    protected static final String COMPLETE_KEY = "/complete";
    protected static final String completeKey = "/complete";

    /**
     * The name of the list item.
     * <p>
     * It is set to final now as we do not allow the change of names for now.
     */
    protected final String name;

    /**
     * If the object is complete or not.
     */
    protected boolean isComplete;

    /**
     * Creates a new task with the given tokens.
     *
     * @param tokens the list of String to be processed.
     * @param delims the keywords after which we retrieve relevant data.
     * @throws InvalidArgumentException if the name is null.
     */
    public Task(String[] tokens, Set<String> delims) throws InvalidArgumentException {
        final Pair<String, Map<String, String>> tmp =
                Singletons.get(TokenUtilities.class).joinTokens(tokens, delims);
        if (tmp.getLeft().isBlank()) {
            throw new InvalidArgumentException("☹ OOPS, the name for a task "
                    + "should not be null", tokens);
        }
        this.name = tmp.getLeft();
        if (tmp.getRight().get(COMPLETE_KEY) != null) {
            this.isComplete = tmp.getRight().get(COMPLETE_KEY).equals("true");
        } else {
            this.isComplete = false;
        }
    }

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
     * If the task contains the given date, i.e. if the task is a deadline,
     * then if the deadline is the same as the given date. If the task is an
     * event, then if the event contains the given date.
     *
     * @param date the date to be checked.
     * @return true if the task contains the date, false otherwise.
     */
    public boolean containsDate(LocalDate date) {
        return false;
    }

    /**
     * Whether if the name of the task contains the given str.
     *
     * @param str the str to be checked.
     * @return true if the name contains the str, false otherwise.
     */
    public boolean nameContains(String str) {
        return this.name.contains(str);
    }

    @Override
    public String serialize() {
        if (isComplete) {
            return name + " " + COMPLETE_KEY + " true";
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

    /**
     * Note: this class has a natural ordering that's different from the equals.
     *
     * @param o the object to be compared.
     * @return 1 if this thing is completed and other is not; -1 if this
     *         thing is not completed but other is; 0 if both are completed or not
     *         completed.
     */
    @Override
    public int compareTo(Task o) {
        // If this thing has been completed but the other has not, then we
        // put this after than the other.
        if (o.isComplete && !this.isComplete) {
            return 1;
        } else if (this.isComplete && !o.isComplete) {
            return -1;
        }
        return 0;
    }
}
