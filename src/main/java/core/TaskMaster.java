package core;

import java.time.LocalDateTime;
import java.util.LinkedList;

import task.Deadline;
import task.Task;

/**
 * core.TaskMaster is the managing class for all Tasks.
 * @author EL
 */
public class TaskMaster {
    private final LinkedList<Task> tasks;

    /**
     * This method is the constructor which creates a TaskMaster instance.
     */
    public TaskMaster() {
        tasks = new LinkedList<>();
    }

    /**
     * This method returns the list of stored Tasks.
     *
     * @return The list of stored Tasks
     */
    public String listAllTasks() {

        StringBuilder ret = new StringBuilder();

        if (tasks.size() > 0) {
            int number = 1;
            for (Task task: tasks) {
                ret.append(String.format("%d.%s\n", number++, task));
            }
        } else {
            ret.append("Oh my, the list is empty!\n");
        }

        return ret.toString();
    }

    /**
     * This method adds the task into the list.
     *
     * @param task The task to be added to this list
     * @return A message indicating that the task has been added
     */
    private String addTask(Task task) {
        StringBuilder ret = new StringBuilder();
        tasks.add(task);
        ret.append("Got it. I've added this task:\n");
        ret.append(task);
        ret.append("\n");
        ret.append(String.format("Now you have %d tasks in the list.\n", tasks.size()));
        return ret.toString();
    }

    /**
     * This method returns the task at the index.
     * Note this expects the index to be a one-indexed value.
     *
     * @param index The index to be accessed
     * @return The task at the index stored in this list
     * @throws exceptions.invalid.Index Thrown when user enters an invalid index
     */
    private Task getTaskAtIndex(int index) throws exceptions.invalid.Index {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new exceptions.invalid.Index(index + 1);
        }
    }

    /**
     * This method marks the task at index with the provided status.
     * Note this expects the index to be a one-indexed value.
     *
     * @param index The task at the index stored in this list
     * @param status The status to set this task to
     * @return A message indicating that the task has been marked to the status
     * @throws exceptions.invalid.Index Thrown when user enters an invalid index
     */
    public String markComplete(int index, boolean status) throws exceptions.invalid.Index {
        StringBuilder ret = new StringBuilder();

        Task task = this.getTaskAtIndex(index);
        task.setComplete(status);
        if (status) {
            ret.append("Nice! I've marked this task as done:\n");
        } else {
            ret.append("OK, I've marked this task as not done yet:\n");
        }
        ret.append(task);

        return ret.toString();
    }

    /**
     * This method deletes the task at the index.
     *
     * @param index The index to be deleted
     * @return A message indicating that the task has been deleted
     * @throws exceptions.invalid.Index Thrown when user enters an invalid index
     */
    public String deleteTask(int index) throws exceptions.invalid.Index {
        try {
            Task remove = tasks.get(index);
            tasks.remove(index);
            return "Noted. I've removed this task:\n" + remove;
        } catch (IndexOutOfBoundsException e) {
            throw new exceptions.invalid.Index(index);
        }
    }

    /**
     * This method creates a Todo task and adds it to the list.
     *
     * @param taskName The name of the Todo
     * @param status The completion status of this ToDo
     * @return A message indicating that this Todo task has been added
     */
    public String addToDo(String taskName, boolean status) {
        return this.addTask(new task.ToDo(taskName, status));
    }

    /**
     * This method creates a Deadline task and adds it to the list.
     *
     * @param taskName The name of this Deadline
     * @param status The completion status of this Deadline
     * @param by The end date time of this Deadline
     * @return A message indicating that this Deadline has been added
     *
     */
    public String addDeadLine(String taskName, boolean status, LocalDateTime by) {
        return this.addTask(new task.Deadline(taskName, status, by));
    }

    /**
     * This method creates an Event task and adds it to the list.
     *
     * @param taskName The name of this Event
     * @param status The completion status of this Event
     * @param from The start date time of this Event
     * @param to The end date time of this Event
     * @return A message indicating that this Event has been added
     */
    public String addEvent(String taskName, boolean status, LocalDateTime from, LocalDateTime to) {
        return this.addTask(new task.Event(taskName, status, from, to));
    }

    /**
     * This method returns CSV formatted list of all the Tasks for exporting.
     *
     * @return list of all Tasks in CSV format.
     */
    public String exportToCsv() {
        StringBuilder ret = new StringBuilder();

        int tmSize = tasks.size();
        for (Task task : tasks) {
            ret.append(task.toCsv());
            tmSize--;
            if (tmSize > 0) {
                ret.append(System.getProperty("line.separator"));
            }
        }
        return ret.toString();
    }

    /**
     * This method returns list of tasks with the keyword
     *
     * @param keyword The keyword to find stored in the list.
     * @return This returns tasks that contains the keyword.
     */
    public String findTask(String keyword) {
        keyword = keyword.toLowerCase();
        StringBuilder ret = new StringBuilder();
        if (tasks.size() > 0) {
            int number = 1;
            for (Task task: tasks) {
                number++;
                if (task.getTaskDescription().toLowerCase().contains(keyword)) {
                    ret.append(String.format("%d.%s\n", number, task));
                }
            }
        } else {
            ret.append("Oh my, the list is empty!\n");
        }

        return ret.toString();
    }

    /**
     * This method returns a string containing tasks with the latest deadline.
     * @return string to be print.
     */
    public String getReminder() {

        StringBuilder ret = new StringBuilder();

        if (tasks.size() > 0) {
            LinkedList<Deadline> latestDeadline = new LinkedList<>();

            for (Task task: tasks) {
                if (task instanceof Deadline) {
                    if (latestDeadline.size() == 0) {
                        latestDeadline.add((Deadline) task);
                    } else {
                        Deadline f = latestDeadline.peek();
                        int comparison = f.getBy().compareTo(((Deadline) task).getBy());
                        if (comparison == 0) {
                            latestDeadline.add((Deadline) task);
                        } else if (comparison < 0) {
                            latestDeadline = new LinkedList<>();
                            latestDeadline.add((Deadline) task);
                        }
                    }
                }
            }

            if (latestDeadline.size() > 0) {
                ret.append("The following tasks are up coming!\n");
            }

            for (Deadline dl : latestDeadline) {
                ret.append(dl);
                ret.append("\n");
            }
        } else {
            ret.append("You have nothing upcoming within this week!\n");
        }

        return ret.toString();
    }

}
