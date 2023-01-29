import java.util.ArrayList;

public class TaskList {
    /**
     * Here we assume all input is in correct format, no error will occur
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
    public void mark(int i) {
        list.get(i).markAsDone();
        s.update(list);
        ui.showMark(list.get(i));
    }
    public void unmark(int i) {
        list.get(i).markAsNotDone();
        s.update(list);
        ui.showMarkNotDone(list.get(i));
    }
    public void list() {
        ui.showList(list);
    }
    public void add(Task t) {
        list.add(t);
        s.write(t);
        ui.showAddTask(list);
    }
    public void delete(int deleteIndex) {
        Task temp = list.remove(deleteIndex);
        s.update(list);
        ui.showDelete(temp, list);
    }
}
