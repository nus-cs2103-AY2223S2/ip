package entities;

import enums.TaskType;
import utils.Marshal;
import utils.Unmarshal;

public class SerializableTask implements Marshal, Unmarshal {
    private final TaskType taskType;
    private final boolean done;
    private final String description;
    private String flags = "";

    public SerializableTask(TaskType taskType, boolean done, String description) {
        this.taskType = taskType;
        this.done = done;
        this.description = description;
    }

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
