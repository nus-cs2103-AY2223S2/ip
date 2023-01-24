package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public String outputList() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            result.append((index == 0 ? "" : "\n") + (index + 1) + ". " + this.tasks.get(index).toString());
        }
        return result.toString();
    }

    public int countTasks() {
        return this.tasks.size();
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Searches for tasks that contains the search keyword
     * @param keyword
     * @return A task list containing tasks which contain the search keyword
     */
    public TaskList find(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curTask = this.tasks.get(i);
            if (curTask.taskName.contains(keyword)) {
                System.out.println(curTask.taskName);
                result.add(curTask);
            }
        }
        return result;
    }

}
