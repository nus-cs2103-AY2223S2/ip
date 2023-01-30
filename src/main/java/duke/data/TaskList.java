package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;


public class TaskList extends ArrayList<Task> {

    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    public TaskList() {
        super();
    }

    public String tasksToStr() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += get(i).storageStr();
            result += System.lineSeparator();
        }
        return result;
    }

    public ArrayList<Task> filterTasks(String findString) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).contains(findString)) {
                result.add(this.get(i));
            }
        }
        return result;
    }
}
