package meggy.task;

import meggy.Resource;
import meggy.exception.MeggyException;

/** {@link UserTask} that has no time whatsoever */
public class TodoTask extends UserTask {
    /** Bracketed icon of task type. */
    public static final String LABEL = getTaskTypeLabel(Resource.CMD_TODO);

    /** @param desc Non-null. Description string of task with command removed. */
    public TodoTask(String desc) throws MeggyException {
        super(desc);
    }

    /** @inheritDoc */
    @Override
    public String recreateCmd() {
        return Resource.CMD_TODO + ' ' + desc;
    }

    /** @inheritDoc */
    @Override
    public String toString() {
        return LABEL + super.toString();
    }

    /** Two {@link TodoTask} objects are equal iff they have same (non-null) description. */
    @Override
    public boolean equals(Object o) {
        return (o instanceof TodoTask) && desc.equals(((TodoTask) o).desc);
    }
}
