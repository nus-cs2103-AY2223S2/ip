package duke;

import duke.exceptions.DukeyException;

/**
 * ToDo is a type of Task which only contains a task name.
 */
public class ToDo extends Task {
    private static final String TYPE = "[T]";

    /**
     * Returns a ToDo with a name and status set to undone.
     * @param name the name of the ToDo
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a ToDo with a name and sets the status accordingly.
     * @param name the name of the ToDo
     * @param isMarked the status of the ToDo
     */
    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }


    /**
     * Returns a ToDo created with details taken from user input.
     * @param ui object to handle user interactions
     * @return ToDo created based on user's input
     * @throws DukeyException on invalid user input for ToDo name
     */
    public static ToDo createToDo(Ui ui) throws DukeyException {
        String toDoName = ui.readTaskName("ToDo");
        return new ToDo(toDoName);
    }

    /**
     * Returns a ToDo created from its log string. This method "loads"
     * a ToDo from a save.
     * @param logStringArray an array of strings where each string is a component of the log string that
     *                       has been split up
     * @return ToDo created from its log string.
     */
    public static ToDo createToDoFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        boolean isMarked = !logStringArray[1].equals("0");
        return new ToDo(name, isMarked);
    }

    /**
     * Returns a message to be printed whenever a ToDo is added.
     * @return the message to be printed
     */
    @Override
    public String getMessageWhenAdded() {
        return "DukeyList just added a new todo:";
    }

    /**
     * Returns the log string of a ToDo, which is a string containing details about the task. The log
     * string will be used to save the task locally when the task list is saved.
     * @return the log string of the ToDo
     */
    @Override
    public String getLogString() {
        return "T" + "," + getMarkedStatus() + "," + this.getName();
    }

    /**
     * Returns a string representation of the ToDo
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName();
        }

        return TYPE + "[ ]" + " " + this.getName();
    }
}

