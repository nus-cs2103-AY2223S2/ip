package meggy;

import java.util.ArrayList;

import meggy.exception.MeggyIobException;
import meggy.task.UserTask;

/** {@link ArrayList} implementation of chat bot task list. Note that task deletion takes O(n) time. */
public class TaskList extends ArrayList<UserTask> {
    public TaskList() {
        super();
    }

    /** @throws MeggyIobException If the parsed index is out of bounds with respect to this tasks list. */
    public void boundsCheck(int idx) throws MeggyIobException {
        final int size = size();
        if (idx < 0 || (idx >= size)) {
            throw new MeggyIobException(idx, size);
        }
    }

    /** @return Printable string representation of this list. */
    @Override
    public String toString() {
        final StringBuilder ans = new StringBuilder();
        int i = 0;
        for (UserTask task : this) {
            ans.append(Resource.idxFmt(i++)).append(task).append('\n');
        }
        return ans.toString();
    }
}
