package Task;
import java.util.ArrayList;
import java.util.Iterator;

import DukeException.DukeException;
import DukeException.TaskNotExistException;
import DukeException.DuplicationException;
import duke.Storage;
import duke.Ui;

/**
 * Class which stores a list of tasks
 * Most operations are done in this class
 */
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

    public String end() {
        return ui.sayBye();
    }

    /**
     * Mark ith task as done
     *
     * @param i
     * @throws DukeException if ith task doesn't exist
     */
    public String mark(int i) {
        try {
            list.get(i).markAsDone();
            assert i >= 0 && i < list.size();
            s.update(list);
            return ui.showMark(list.get(i));
        } catch (IndexOutOfBoundsException E) {
            DukeException e = new TaskNotExistException();
            return e.toString();
        }
    }

    /**
     * Unmark ith task as not done
     *
     * @param i
     */
    public String unmark(int i) {
        try {
            list.get(i).markAsNotDone();
            assert i >= 0 && i < list.size();
            s.update(list);
            return ui.showMarkNotDone(list.get(i));
        } catch (IndexOutOfBoundsException E) {
            DukeException e = new TaskNotExistException();
            return e.toString();
        }
    }

    public String list() {
        return ui.showList(list);
    }

    /**
     * Adds task to list
     *
     * @param t
     */
    public String add(Task t) {
        if (checkDuplication(t)) {
            DukeException e = new DuplicationException();
            return e.toString();
        }
        list.add(t);
        s.write(t);
        return ui.showAddTask(list);
    }

    /**
     * Deletes ith task from list
     *
     * @param deleteIndex
     * @throws DukeException if ith task doesn't exist
     */
    public String delete(int deleteIndex) {
        try {
            Task temp = list.remove(deleteIndex);
            s.update(list);
            return ui.showDelete(temp, list);
        } catch (IndexOutOfBoundsException E) {
            DukeException e = new TaskNotExistException();
            return e.toString();
        }
    }

    /**
     * List out the tasks which contain keywords
     *
     * @param keywords
     */
    public String find(String keywords) {
        ArrayList<Task> matches = new ArrayList<>();
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.getDescriptionAndTime().contains(keywords)) {
                matches.add(t);
            }
        }
        assert it.hasNext() == false;
        return ui.showFind(matches);
    }

    /**
     * Check if added task t has duplication within task list
     * If duplication happens but the task in task list is done already, return false
     * @param t -> added task
     * @return true if duplicate found
     */
    public boolean checkDuplication(Task t) {
        for (Task temp: list) {
            if (temp.isDone) {
                return false;
            }
            if (temp.equals(t)) {
                return true;
            }
        }
        return false;
    }

}
