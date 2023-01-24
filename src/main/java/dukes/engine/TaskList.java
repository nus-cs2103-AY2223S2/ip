package dukes.engine;

import java.util.*;

public class TaskList {
    private List<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
