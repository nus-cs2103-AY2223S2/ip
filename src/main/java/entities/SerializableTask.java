package entities;

import enums.TaskType;
import utils.IMarshal;
import utils.IUnmarshal;

/**
 * Represents a serializable task.
 */
public class SerializableTask implements IMarshal<String>, IUnmarshal<Task> {
    private final TaskType taskType;
    private final boolean done;
    private final String description;
    private String flags = "";

    /**
     * Initialize a Serializable Task.
     *
     * @param taskType The type of task.
     * @param done The status of task.
     * @param description The description of task.
     */
    public SerializableTask(TaskType taskType, boolean done, String description) {
        this.taskType = taskType;
        this.done = done;
        this.description = description;
    }

    /**
     * Initialize a Serializable Task.
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
        StringBuilder sb = new StringBuilder(taskType.getType() + " | " + (done ? "1" : "0") + " | " + description);
        if (!flags.isBlank()) {
            sb.append(" | ").append(flags);
        }
        return sb.toString();
    }

    @Override
    public Task unmarshal() {
        switch (taskType) {
        case TODO: return new Todo(description);
        case DEADLINE: return new Deadline(description, flags);
        case EVENT: return new Event(description, flags.split(" to "));
        default: return null;
        }
    }
}
