package duke.storage.serializer;

import duke.exception.DukeException;
import duke.task.Task;

@FunctionalInterface
public interface Deserializer {
    abstract public Task deserialize(Serializer serializer) throws DukeException;
}
