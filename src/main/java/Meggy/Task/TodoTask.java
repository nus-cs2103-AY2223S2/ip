package Meggy.Task;

import Meggy.Exception.MeggyException;
import Meggy.Resource;

/**
 * {@link UserTask} that has no time whatsoever
 */
public class TodoTask extends UserTask {
    /**
     * Bracketed icon of task type.
     */
    public final static String label = getTaskTypeLabel(Resource.cmdTodo);

    /**
     * @param desc Non-null. Description string of task with command removed.
     */
    public TodoTask(String desc) throws MeggyException {
        super(desc);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String encode() {
        return Resource.cmdTodo + ' ' + desc;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return label + super.toString();
    }

    /**
     * Two {@link TodoTask} objects are equal iff they have same (non-null) description.
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof TodoTask) && desc.equals(((TodoTask) o).desc);
    }
}
