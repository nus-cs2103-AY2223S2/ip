package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 8098680977751428278L;

    private List<Task> taskList = new ArrayList<>();

    public int getNumTasks() {
        return taskList.size();
    }

    public TaskList() {}

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void printAll() {
        for (int i = 0; i < taskList.size(); i++) {
            String printedString = String.format("%d. %s ", i + 1, taskList.get(i).toString());
            System.out.println(printedString);
        }
    }

    /**
     * Adds a new task into the list of tasks.
     * 
     * @param task a {@code Task} instance.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Storage.writeTaskList(this);
    }

    /**
     * Deletes an existing task from the list of tasks.
     * 
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the deleted task.
     */
    public String deleteTasks(int taskIndex) {
        Task targetTask = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        Storage.writeTaskList(this);
        return targetTask.toString();
    }

    /**
     * Marks an existing task as done from the list of tasks.
     * 
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the marked task.
     */
    public String markTask(int taskIndex) {
        Task targetTask = taskList.get(taskIndex - 1);
        targetTask.mark();
        taskList.set(taskIndex - 1, targetTask);
        Storage.writeTaskList(this);
        return targetTask.toString();
    }

    /**
     * Marks an existing task as not done from the list of tasks.
     * 
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the unmarked task.
     */
    public String unmarkTask(int taskIndex) {
        Task targetTask = taskList.get(taskIndex - 1);
        targetTask.unmark();
        taskList.set(taskIndex - 1, targetTask);
        Storage.writeTaskList(this);
        return targetTask.toString();
    }

    public TaskList find(String keyword) {
        List<Task> filteredTaskList = taskList.stream().filter((task) -> task.contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredTaskList);
    }

}
