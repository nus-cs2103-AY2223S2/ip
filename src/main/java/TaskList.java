import java.util.ArrayList;
import java.util.List;

class TaskList {
    List<Pair<String, Boolean>> listTask;

    public TaskList() {
        this.listTask = new ArrayList<Pair<String, Boolean>>();
    }

    TaskList markDone(int index) {
        Pair currentPair = this.listTask.get(index);
        Pair newPair = new Pair(currentPair.first(), true);
        this.listTask.set(index, newPair);
        return this;
    }

    TaskList unMark(int index) {
        Pair currentPair = this.listTask.get(index);
        Pair newPair = new Pair(currentPair.first(), false);
        this.listTask.set(index, newPair);
        return this;
    }

    TaskList addTask(String task) {
        this.listTask.add(new Pair(task, false));
        return this;
    }

    String getTask(int index) {
        return this.listTask.get(index).first();
    }

    @Override
    public String toString() {
        if (this.listTask.isEmpty()) {
            return "NOTHING ADDED TO LIST";
        } else {
            String toPrintOut = "";
            for (int i = 0; i < this.listTask.size(); i++) {
                Pair currentPair = this.listTask.get(i);
                if (currentPair.second().equals(true)) {
                    toPrintOut += (i + 1) + ". [X] " + this.listTask.get(i).first() + '\n';
                } else {
                    toPrintOut += (i + 1) + ". [ ] " + this.listTask.get(i).first() + '\n';
                }
            }
            return toPrintOut;
        }
    }
}