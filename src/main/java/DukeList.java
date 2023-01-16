import java.util.ArrayList;

public class DukeList {

    public ArrayList<DukeTask> list = new ArrayList<>();

    public ArrayList<DukeTask> getList() {
        return list;
    }

    public void addTask(String task) {
        DukeTask newTask = new DukeTask(task);
        list.add(newTask);
    }

    public DukeTask mark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        DukeTask task = list.get(taskNumber);
        task.mark();
        return task;
    }

    public DukeTask unmark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        DukeTask task = list.get(taskNumber);
        task.unmark();
        return task;
    }
}
