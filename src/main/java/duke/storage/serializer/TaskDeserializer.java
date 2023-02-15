package duke.storage.serializer;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * TaskDeserializer is a functional interface used to deserialize a TaskSerializer into a Task
 */
@FunctionalInterface
public interface TaskDeserializer {
    Task deserialize(TaskSerializer taskSerializer) throws DukeException;
}
