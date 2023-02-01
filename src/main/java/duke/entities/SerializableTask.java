package duke.entities;

import duke.enums.TaskType;
import duke.utils.ISerializable;

/**
 * Represents a serializable task.
 */
public class SerializableTask implements ISerializable<String, Task> {
    /** Serializable task attributes **/
    private final TaskType taskType;
    private final boolean isDone;
    private final String description;
    private String flags = "";

    /**
     * Initializes a Serializable Task.
     *
     * @param taskType The type of task.
     * @param done The status of task.
     * @param description The description of task.
     */
    public SerializableTask(TaskType taskType, boolean done, String description) {
        this.taskType = taskType;
        this.isDone = done;
        this.description = description;
    }

    /**
     * Initializes a Serializable Task.
     *
     * @param taskType The type of task.
     * @param done The status of task.
     * @param description The description of task.
     * @param flags Additional flags of task.
     */
    public SerializableTask(TaskType taskType, boolean done, String description, String flags) {
        this(taskType, done, description);
        this.flags = flags;
    }

    @Override
    public String marshal() {
        StringBuilder sb = new StringBuilder(taskType.getType() + " | " + (isDone ? "1" : "0") + " | " + description);
        if (!flags.isBlank()) {
            sb.append(" | ").append(flags);
        }
        return sb.toString();
    }

    @Override
    public Task unmarshal() {
        Task task;
        switch (taskType) {
        case TODO:
            task = new Todo(description);
            break;
        case DEADLINE:
            task = new Deadline(description, flags);
            break;
        case EVENT:
            task = new Event(description, flags.split(" to "));
            break;
        default:
            task = null;
        }
        if (isDone && task != null) {
            task.markTask();
        }
        return task;
    }
}
