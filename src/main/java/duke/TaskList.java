package duke;
import java.util.ArrayList;

public class TaskList<Task> {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    void addTask(Task task) {
        this.taskList.add(task);
    }

    Task get(int index) {
        return taskList.get(index);
    }

    void removeTask(int index) throws NeroException {
        try {
            this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NeroException("Please enter a valid index from 0 to " + (this.taskList.size() - 1));
        }
    }

    void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i));
        }
    }

    int getSize() {
        return taskList.size();
    }
}
