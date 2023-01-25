package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will create a list to contain all the Task
 */
public class TaskList {
    private static List<Task> taskLists;
    private static List<Task> searchLists = new ArrayList<>();

    /**
     * This is a constructor for TaskList
     */
    public TaskList() {
        taskLists = new ArrayList<>(100);
    }

    public static void find(String description) {
        searchLists.clear();
        for (Task task: Task.tasks) {
            if (task.description.contains(description)) {
                searchLists.add(task);
            }
        }
    }

    public static void printFind() {
        int count = 1;
        if (searchLists.size() == 0) {
            System.out.println("Nothing found");
        }
        for (int i = 0; i < searchLists.size();i++) {
            System.out.println(""+ count + "." + searchLists.get(i));
            count += 1;
        }
    }
    /**
     * Adds task into tasklists
     * @param task that will be added into tasklists
     */
    public void add(Task task) {
        taskLists.add(task);
    }

    /**
     * Gets the task based on index
     * @param index index of task
     * @return the task of that index
     */
    public Task get(int index){
        return taskLists.get(index);
    }

    /**
     * Removes task from tasklists
     * @param task task that is removed
     */
    public void remove(Task task){
        taskLists.remove(task);
    }
}