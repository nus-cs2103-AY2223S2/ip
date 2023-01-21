package aqua.manager;

import java.util.ArrayList;

import aqua.aquatask.AquaTask;

/** Manager of tasks. */
public class TaskManager {
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
