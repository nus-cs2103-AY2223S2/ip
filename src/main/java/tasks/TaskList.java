package tasks;

import java.util.ArrayList;

import exceptions.InvalidIndexException;
import exceptions.NoSuchTaskException;

/**
 * Represents a task list which stores all the tasks for Duke.
 */
public class TaskList {
    private static final String ADDTASKMSG = "Got it. Added this task, I have:";
    private ArrayList<Task> taskList;
    /**
     * Constructor for Task List.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Constructor for Task List.
     * @param deadlines deadline tasks to be added into task list at initialisation
     */
    public TaskList(ArrayList<? extends Task> deadlines) {
        this.taskList = new ArrayList<>();
        taskList.addAll(deadlines);
    }

    /**
     * Adds task to the task list String array.
     * @param task name of the task
     */
    public void addTask(Task task) {
        System.out.printf("%s\n %s%n", ADDTASKMSG, task.toString());
        addTaskSilent(task);
        System.out.printf("%d tasks in the list, you have now.%n", getSize());
    }
    public Task get(String idx) {
        try {
            int index = Integer.parseInt(idx) - 1;
            return taskList.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds task to task list without any messages
     * @param task task object
     */
    public void addTaskSilent(Task task) {
        this.taskList.add(task);
    }

    /**
     * Outputs the size of non-null Task Objects in Task List.
     * @return number of tasks in the task list
     */
    public int getSize() {
        return this.taskList.size();
    }


    /**
     * Returns a task list containing tasks filtered by a keyword based on their
     * string representation.
     * @param keyword the keyword to be used for search
     * @return task list containing tasks filtered by a keyword based on their string representation
     */
    public TaskList findByKeyword(String keyword) {
        TaskList filteredTasks = new TaskList();
        this.taskList.stream()
                .filter(x-> x.toString().trim().contains(keyword))
                .forEach(filteredTasks::addTaskSilent);
        if (filteredTasks.getSize() == 0) {
            System.out.println("Empty the Jedi Archives are, the task you seek");
        } else {
            System.out.printf("%d tasks in the Jedi Archives, I find%n", filteredTasks.getSize());
        }
        return filteredTasks;
    }
    public ArrayList<Deadline> getDeadlineTasks() {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        taskList.stream()
                .filter(x -> x.getTaskType().equals("D"))
                .forEach(task -> {
                    Deadline deadline = (Deadline) task;
                    deadlineTasks.add(deadline);
                });
        return deadlineTasks;
    }

    /**
     * Deletes a task in task list by their index counting from 1
     * @param indexString string index
     */
    public void deleteTask(String indexString) {
        int idx;
        try {
            idx = Integer.parseInt(indexString);
            if (idx > this.getSize()) {
                throw new IndexOutOfBoundsException();
            }
            System.out.printf("Noted. Removed this task I have:\n %s%n",
                    this.taskList.get(idx - 1));
            this.taskList.remove(idx - 1);
            System.out.printf("%d tasks in the list, you now have%n", getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("In this index, no such task I found", null);
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", indexString), null);
        }
    }

    /**
     * Marks a task by its index in the array of Tasks.
     * @param index index of interest to mark the task as done
     */
    public void markTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            this.taskList.get(idx).mark();
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", index), null);
        }
    }

    /**
     * Unmarks a task by its index in the array of Tasks.
     * @param index index of interest to unmark the task as undone.
     */
    public void unmarkTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            this.taskList.get(idx).unmark();
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", index), null);
        }
    }

    /**
     * Prints the names of tasks in the list in the top-down list format with numbered indexes
     * starting from 1 added in chronological order whereas returns empty list string if task list is empty.
     */
    public String listItems() {
        if (this.getSize() == 0) {
            System.out.println("Empty, this list is !");
            return "Empty, this list is !";
        } else {
            String out = "";
            for (int i = 0; i < this.getSize() - 1; i++) {
                out += String.format("%d.%s\n", i + 1, taskList.get(i).toString());
            }
            System.out.println(out + String.format("%d.%s", this.getSize(),
                    taskList.get(this.getSize() - 1).toString()));
            return out + String.format("%d.%s", this.getSize(),
                    taskList.get(this.getSize() - 1).toString());
        }
    }
    /**
     * Getter method to retrieve the task list.
     * @return task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
