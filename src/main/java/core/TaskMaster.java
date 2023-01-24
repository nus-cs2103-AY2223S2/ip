package core;
import task.Task;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * core.TaskMaster is the managing class for all Tasks.
 */
public class TaskMaster {
    private final LinkedList<Task> LIST;

    /**
     * Constructor which creates a TaskMaster instance.
     */
    public TaskMaster() {
        LIST = new LinkedList<>();
    }

    /**
     * Returns the list of stored Tasks.
     *
     * @return The list of stored Tasks
     */
    public String list() {

        StringBuilder ret = new StringBuilder();

        if (LIST.size() > 0) {
            int number = 1;
            for (Task task: LIST) {
                ret.append(String.format("%d.%s\n", number++, task));
            }
        } else {
            ret.append("Oh my, the list is empty!\n");
        }

        return ret.toString();
    }

    /**
     * Adds the task into the list.
     *
     * @param task The task to be added to this list
     * @return A message indicating that the task has been added
     */
    private String add(Task task) {
        StringBuilder ret = new StringBuilder();
        LIST.add(task);
        ret.append("Got it. I've added this task:\n");
        ret.append(task);
        ret.append(String.format("Now you have %d tasks in the list.\n", LIST.size()));
        return ret.toString();
    }

    /**
     * Returns the task at the index.
     * Note this expects the index to be a one-indexed value.
     *
     * @param index The index to be accessed
     * @return The task at the index stored in this list
     * @throws exceptions.invalid.Index Thrown when user enters an invalid index
     */
    private Task getTaskAtIndex(int index) throws exceptions.invalid.Index {
        try {
            return LIST.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new exceptions.invalid.Index(index);
        }
    }

    /**
     * Marks the task at index with the provided status.
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
        task.status = status;
        if (status) {
            ret.append("Nice! I've marked this task as done:\n");
        } else {
            ret.append("OK, I've marked this task as not done yet:\n");
        }
        ret.append(task);

        return ret.toString();
    }

    /**
     * Deletes the task at the index.
     *
     * @param index The index to be deleted
     * @return A message indicating that the task has been deleted
     * @throws exceptions.invalid.Index
     */
    public String delete(int index) throws exceptions.invalid.Index {
        try {
            LIST.remove(index);
            return "Noted. I've removed this task:";
        } catch (IndexOutOfBoundsException e) {
            throw new exceptions.invalid.Index(index);
        }
    }

    /**
     * Creates a Todo task and adds it to the list.
     *
     * @param taskName The name of the Todo
     * @param status The completion status of this ToDo
     * @return A message indicating that this Todo task has been added
     */
    public String addToDo(String taskName, boolean status) {
        return this.add(new task.ToDo(taskName, status));
    }


    /**
     * Creates a Deadline task and adds it to the list.
     *
     * @param taskName The name of this Deadline
     * @param status The completion status of this Deadline
     * @param by The end date time of this Deadline
     * @return A message indicating that this Deadline has been added
     *
     */
    public String addDeadLine(String taskName, boolean status, LocalDateTime by) {
        return this.add(new task.Deadline(taskName, status, by));
    }

    /**
     * Creates an Event task and adds it to the list.
     *
     * @param taskName The name of this Event
     * @param status The completion status of this Event
     * @param from The start date time of this Event
     * @param to The end date time of this Event
     * @return A message indicating that this Event has been added
     */
    public String addEvent(String taskName, boolean status, LocalDateTime from, LocalDateTime to) {
        return this.add(new task.Event(taskName, status, from, to));
    }

    /**
     * Returns CSV formatted list of all the Tasks for exporting.
     *
     * @return list of all Tasks in CSV format.
     */
    public String export(){
        StringBuilder ret = new StringBuilder();

        int tmSize = LIST.size();
        for (Task task : LIST ){
            ret.append(task.toCSV());
            tmSize--;
            if (tmSize > 0) {
                ret.append(System.getProperty("line.separator"));
            }
        }
        return ret.toString();
    }
}
