package features.event_manager;

/**
 * This shall resemble a Task. The reason that no modifiers was added was
 * because this class shall be package private.
 */
abstract class Task {
    /**
     * Creates a new Task instance.
     * @param name the name of the list item.
     * @param isComplete if the Task is complete or not.
     */
    Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    /**
     * Initializes a new Task instance, with its isComplete set to false.
     * @param name the name of the Task instance.
     */
    Task(String name) {
        this(name, false);
    }

    /**
     * The name of the list item.
     *
     * It is set to final now as we do not allow the change of names for now.
     */
    private final String name;

    /**
     * If the object is complete or not.
     */
    private boolean isComplete;

    /**
     * Sets the isComplete to complete.
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
