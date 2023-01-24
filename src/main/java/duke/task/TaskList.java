package duke.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A TaskList class that encapsulates the information and actions of a task list.
 */
public class TaskList {
    private final List<DukeTask> list;

    /**
     * Constructor of the TaskList class that create new Arraylist.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<DukeTask> list) {
        this.list = list;
    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param task The TaskList to be added
     */
    public void addTask(DukeTask task) {
        this.list.add(task);
    }

    /**
     * Delete the task of the given index.
     *
     * @param taskIndex The index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        this.list.remove(taskIndex);
    }

    /**
     * Indicates the number of the task on the list.
     *
     * @return The number of the task on the list
     */
    public int getNoOfTasks() {
        return this.list.size();
    }

    /**
     * Gets the Task of the given index from the TaskList.
     *
     * @param index The index of the task to be obtained
     * @return The task of the given index
     */
    public DukeTask getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Extracts all incomplete deadline tasks from the task list and returns them as a new TaskList.
     * The tasks are sorted by their deadlines in ascending order.
     *
     * @return A new TaskList containing all incomplete deadline tasks from the original list
     */
    public TaskList extractDeadlines() {
        List<DukeTask> result = this.list.stream()
                .filter(task -> task.getType() == TaskType.DEADLINE && !task.getStatus())
                .sorted(Comparator.comparing(x -> {
                    DeadlineTask ddlTask = (DeadlineTask) x;
                    return ddlTask.getDeadline();
                }))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(result));
    }

    /**
     * Returns a string representation of the task list in the format "index. task\n".
     *
     * @return A string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < this.getNoOfTasks(); i++) {
            listContent.append(i + 1).append(".").append(this.getTask(i)).append("\n");
        }
        return String.valueOf(listContent);
    }
}
