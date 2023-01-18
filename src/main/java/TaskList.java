import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        assert taskList.size() != 0: "List is empty!";
        return taskList.get(index - 1);
    }

    // Add tasks depending on the different types of events



}
