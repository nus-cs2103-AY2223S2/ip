package duke.task;

import java.util.HashMap;

import duke.DukeException;

/**
 * Class representing a Task.
 */
public abstract class Task {
    /**
     * Enum representing the task icon.
     */
    public enum TaskIcon {
        TODO("T"),
        EVENT("E"),
        DEADLINE("D");
        private static final HashMap<String, TaskIcon> STRING_TO_ENUM_MAP = new HashMap<>();
        private final String iconString;

        static {
            for (TaskIcon taskIcon : values()) {
                STRING_TO_ENUM_MAP.put(taskIcon.getIconString(), taskIcon);
            }
        }

        TaskIcon(String iconString) {
            this.iconString = iconString;
        }

        public static TaskIcon valueOfIconString(String iconString) {
            return STRING_TO_ENUM_MAP.get(iconString);
        }

        /**
         * Getter function for the symbol of the enum
         *
         * @return Symbol string
         */
        public String getIconString() {
            return iconString;
        }
    };
    protected String description;
    protected String tags = null;
    protected boolean isDone;
    protected TaskIcon taskIcon;

    /**
     * Returns a Task object.
     *
     * @param description Description of the task.
     */
    protected Task(String description, TaskIcon taskIcon) throws DukeException {
        this.description = description;
        this.isDone = false;
        this.taskIcon = taskIcon;
        if (this.description.equals("")) {
            throw new DukeException("The description of a Task cannot be empty.");
        }
    }

    protected Task(String description, TaskIcon taskIcon, String tags) throws DukeException {
        this(description, taskIcon);
        this.tags = tags.trim();
        if (this.tags.equals("")) {
            throw new DukeException("The tags of a Task cannot be empty.");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public boolean hasTags() {
        return tags != null;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskIcon getTaskIcon() {
        return this.taskIcon;
    }

    @Override
    public String toString() {
        String baseString = String.format(
                "[%s][%s] %s",
                this.taskIcon.getIconString(),
                getStatusIcon(),
                getDescription()
        );
        if (hasTags()) {
            return String.format("%s [%s]", baseString, getTags());
        }
        return baseString;
    }

}
