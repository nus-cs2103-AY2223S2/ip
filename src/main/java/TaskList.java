import java.util.ArrayList;
import java.util.List;

class TaskList {
    List<Pair<String, Boolean>> listTask;

    public TaskList() {
        this.listTask = new ArrayList<Pair<String, Boolean>>();
    }

    TaskList markDone(int index) {
        return this;
    }

    TaskList addTask(String task) {
        this.listTask.add(new Pair(task, false));
        return this;
    }

    @Override
    public String toString() {
        if (this.listTask.isEmpty()) {
            return "NOTHING ADDED TO LIST";
        } else {
            String toPrintOut = "";
            for (int i = 0; i < this.listTask.size(); i++) {
                toPrintOut += (i + 1) + ". " + this.listTask.get(i).first() + '\n';
            }
            return toPrintOut;
        }
    }
}