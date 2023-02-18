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
            ans.append(Resource.fmtIdx(i++)).append(task).append('\n');
        }
        return ans.toString();
    }

    /** @return The command list that would add all the tasks. Each task completion status is kept. */
    public String recreateCmds() {
        final StringBuilder ans = new StringBuilder();
        int i = 0;
        for (UserTask task : this) {
            i++;
            ans.append(task.recreateCmd()).append('\n');
            if (task.isDone()) {
                ans.append(Resource.CMD_MARK).append(' ').append(i).append('\n');
            }
        }
        return ans.toString();
    }
}
