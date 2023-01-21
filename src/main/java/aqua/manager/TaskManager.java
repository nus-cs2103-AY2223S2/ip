package aqua.manager;

import java.util.ArrayList;

import aqua.aquatask.AquaTask;
import aqua.storage.Reloadable;

/** Manager of tasks. */
public class TaskManager implements Reloadable {
    private final ArrayList<AquaTask> taskList = new ArrayList<>();


    public void add(AquaTask task) {
        taskList.add(task);
    }

    
    public AquaTask mark(int taskNum, boolean isComplete) throws IndexOutOfBoundsException {
        taskList.set(taskNum, taskList.get(taskNum).mark(isComplete));
        return taskList.get(taskNum);
    }


    public AquaTask delete(int taskNum) throws IndexOutOfBoundsException {
        return taskList.remove(taskNum);
    }


    public int size() {
        return taskList.size();
    }


    @Override
    public String getReloadString() {
        StringBuilder builder = new StringBuilder();
        for (AquaTask task : taskList) {
            builder.append(task.getReloadString());
            builder.append("\n");
        }
        return builder.toString().strip();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format(
                "%d. %s\n",
                i+1,
                taskList.get(i).toString()
            ));
        }
        return builder.toString();
    }
}
