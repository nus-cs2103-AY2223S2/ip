import java.util.ArrayList;
import java.util.List;

class TaskList {
    List<Task> listTask;

    public TaskList() {
        this.listTask = new ArrayList<Task>();
    }

    TaskList markDone(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.markDone());
        return this;
    }

    TaskList unMark(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.unMark());
        return this;
    }

    TaskList addTask(Task task) {
        this.listTask.add(task);
        return this;
    }

    String getTask(int index) {
        return this.listTask.get(index).toString();
    }

    @Override
    public String toString() {
        if (this.listTask.isEmpty()) {
            return "NOTHING ADDED TO LIST";
        } else {
            String toPrintOut = "";
            for (int i = 0; i < this.listTask.size(); i++) {
                toPrintOut += (i + 1) + ". " + this.listTask.get(i).toString() + '\n';
            }
            return toPrintOut;
        }
    }
}