package duke.storage.serializer;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskSerializer extends Serializer {
    private static final String CATEGORY_KEY = "category";

    public TaskSerializer() {
        super();
    }

    public TaskSerializer(String category) {
        super();
        add(CATEGORY_KEY, category);
    }

    public Task createTask() throws DukeException {
        String icon = get(CATEGORY_KEY).toString();
        if (Deadline.hasIcon(icon)) {
            return Deadline.getDeserializer().deserialize(this);
        } 
        if (Event.hasIcon(icon)) {
            return Event.getDeserializer().deserialize(this);
        }
        if (Todo.hasIcon(icon)) {
            return Todo.getDeserializer().deserialize(this);
        }
        throw new DukeException("Unable to create task from TaskSerializer");
    }
}
