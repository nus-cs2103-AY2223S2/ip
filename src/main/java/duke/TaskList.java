package duke;


import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    /**
     * Here we assume all input is in correct format
     * Moreover, all index is correct for compile
     * Error handling and index handling (i - 1) will be done by parser
     */
    private ArrayList<Task> list;
    private Ui ui = new Ui();
    private Storage s = new Storage();

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void end() {
        ui.sayBye();
    }

    public void mark(int i) throws DukeException {
        try {
            list.get(i).markAsDone();
            s.update(list);
            ui.showMark(list.get(i));
        } catch (IndexOutOfBoundsException IOBE) {
            throw new DukeException(ExceptionType.TASK_NOT_EXIST);
        }

    }

    public void unmark(int i) throws DukeException {
        try {
            list.get(i).markAsNotDone();
            s.update(list);
            ui.showMarkNotDone(list.get(i));
        } catch (IndexOutOfBoundsException IOBE) {
            throw new DukeException(ExceptionType.TASK_NOT_EXIST);
        }
    }

    public void list() {
        ui.showList(list);
    }

    public void add(Task t) {
        list.add(t);
        s.write(t);
        ui.showAddTask(list);
    }

    public void delete(int deleteIndex) throws DukeException {
        try {
            Task temp = list.remove(deleteIndex);
            s.update(list);
            ui.showDelete(temp, list);
        } catch (IndexOutOfBoundsException IOBE) {
            throw new DukeException(ExceptionType.TASK_NOT_EXIST);
        }
    }

    /**
     * List out the tasks which contain keywords
     * @param keywords
     */
    public void find(String keywords) {
        ArrayList<Task> matches = new ArrayList<>();
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.getDescriptionAndTime().contains(keywords)) {
                matches.add(t);
            }
        }
        ui.showFind(matches);
    }
}
