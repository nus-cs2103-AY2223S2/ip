package Duke.task;

import java.util.ArrayList;

/**
 * Class manages tasks in an Arraylist
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
   * Constructor for TaskList
   * initialize the field tasks ,
   * so it's not null.
   */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
   * method to add a new task into the arraylist.
   *
   * @param task receives the task needed to be added.
   */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
   * method to delete a task from the arraylist.
   *
   * @param index receives the index of the task needed to be deleted.
   */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
   * method to get how many tasks are in the TaskList.
   */
    public int size() {
        return tasks.size();
    }

    /**
   * return the task of a particular index in the list.
   *
   * @param curIndex receives the index of the task to get.
   */
    public Task get(int curIndex) {
        return tasks.get(curIndex);
    }

    /**
   * list out the tasks in the list with their string information.
   */
    public String listTask() {
        StringBuilder sb = new StringBuilder();
        for (Task t: tasks) {
            int num = tasks.indexOf(t) + 1;
            sb.append(num).append(".").append(t.taskString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Sort Events in taskList by time
     * @return a list of events sorted by start time.
     */
    public ArrayList<Event> sortEvents() {
        ArrayList<Event> eventsList = new ArrayList<>();
        for (Task t: tasks) {
            if (t instanceof Event) {
                eventsList.add((Event) t);
            }
        }
        eventsList.sort(new EventComparator());
        return eventsList;
    }

    /**
     * Sort Deadlines in taskList by time
     * @return a list of deadlines sorted by time.
     */
    public ArrayList<Deadline> sortDeadlines() {
        ArrayList<Deadline> deadlineList = new ArrayList<>();
        for (Task t: tasks) {
            if (t instanceof Deadline) {
                deadlineList.add((Deadline) t);
            }
        }
        deadlineList.sort(new DeadlineComparator());
        return deadlineList;
    }
    /**
   * convert the current taskList into String commands,
   * which will be written into the data file in the future.
   */
    public String arrayListToString() {
        StringBuilder arrayString = new StringBuilder();
        for (Task t : tasks) {
            arrayString.append(t.toString()).append(" ");
            arrayString.append(t.getString());
            if (t instanceof Deadline) {
                arrayString.append(" /by").append(" ")
                .append(((Deadline) t).getStrTime());
            }
            if (t instanceof Event) {
                arrayString.append(" /from").append(" ")
                .append(((Event) t).getStrStartTime())
                .append(" ").append(" /to").append(" ")
                .append(((Event) t).getStrEndTime());
            }
            arrayString.append("\n");
            if (t.isMark()) {
                arrayString.append("mark ")
                        .append(tasks.indexOf(t) + 1).append("\n");
            }
        }
        return arrayString.toString();
    }
}

