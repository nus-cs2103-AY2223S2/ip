package duke.storage.serializer;

import duke.exception.DukeException;
import duke.task.Task;

@FunctionalInterface
public interface Deserializer {
    public abstract Task deserialize(Serializer serializer) throws DukeException;
}
