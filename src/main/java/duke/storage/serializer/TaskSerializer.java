package duke.storage.serializer;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskSerializer the serialization and deserialization of tasks.
 */
public class TaskSerializer extends Serializer {
    public TaskSerializer() {
        super();
    }

    public TaskSerializer(String serialized) {
        super(serialized);
    }

    /**
     * Creates and returns a task defined by instance
     *
     * @return Task defined by instance
     * @throws DukeException
     */
    public Task createTask() throws DukeException {
        if (Deadline.canDeserialize(this)) {
            return Deadline.getDeserializer().deserialize(this);
        }
        if (Event.canDeserialize(this)) {
            return Event.getDeserializer().deserialize(this);
        }
        if (Todo.canDeserialize(this)) {
            return Todo.getDeserializer().deserialize(this);
        }
        throw new DukeException("Unable to create task from Serializer");
    }
}
