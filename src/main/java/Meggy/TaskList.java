package Meggy;

import Meggy.Exception.MeggyIOBException;
import Meggy.Task.UserTask;

import java.util.ArrayList;

/**
 * {@link ArrayList} implementation of chat bot task list. Note that task deletion takes O(n) time.
 */
public class TaskList extends ArrayList<UserTask> {
    public TaskList() {
        super();
    }

    /**
     * @throws MeggyIOBException If the parsed index is out of bounds with respect to this tasks list.
     */
    public void boundsCheck(int idx) throws MeggyIOBException {
        final int size = size();
        if (idx < 0 || (idx >= size))
            throw new MeggyIOBException(idx, size);
    }

    /**
     * @return Response to 'list' command.
     */
    @Override
    public String toString() {
        final StringBuilder ans = new StringBuilder(Resource.notifList);
        int i = 0;
        for (UserTask task : this)
            ans.append(Resource.idxFmt(i++)).append(task).append('\n');
        return ans.toString();
    }
}
