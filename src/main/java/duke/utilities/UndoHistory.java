package duke.utilities;

import java.util.ArrayList;
import duke.exceptions.DukeEmptyUndoHistoryException;
import duke.tasks.Task;

public class UndoHistory {

    private final ArrayList<ArrayList<Task>> history;

    public UndoHistory() {
        history = new ArrayList<>();
    }

    public ArrayList<Task> popLastState() throws DukeEmptyUndoHistoryException {
        if (isEmpty()) {
            throw new DukeEmptyUndoHistoryException();
        }

        return history.remove(history.size() - 1);
    }

    public void addNewState(ArrayList<Task> tasks) {
        ArrayList<Task> state = new ArrayList<>();

        for (Task task : tasks) {
            Task clone = task.deepClone();
            state.add(clone);
        }

        history.add(state);
    }

    private boolean isEmpty() {
        return history.size() == 0;
    }
}
