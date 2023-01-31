import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> arrOfTask;

    public TaskList(ArrayList<Task> arrOfTask) {
        this.arrOfTask = arrOfTask;
    }

    public void getTaskForToday() {
        for (Task t: arrOfTask) {
            if (t.getDate().equals(LocalDate.now())) {
                System.out.println(t);
            }
        }
    }

    public int getTotalNumberOfTask() {
        return arrOfTask.size();
    }
    public void addTask(Task t) {
        arrOfTask.add(t);
    }

    public Task taskDone(int index) {
        Task t = arrOfTask.get(index);
        t.taskDone();
        return t;
    }

    public Task taskNotDone(int index) {
        Task t = arrOfTask.get(index);
        t.taskNotDone();
        return t;
    }

    public Task deleteTask(int index) {
        Task t = arrOfTask.get(index);
        arrOfTask.remove(index);
        return t;
    }

    public Task getTaskAtIndex(int index) {
        return arrOfTask.get(index);
    }

    public void writeToFile(FileWriter fw) throws IOException {
        for (Task t : arrOfTask) {
            fw.write(t.toText() + "\n");
        }
    }
}
