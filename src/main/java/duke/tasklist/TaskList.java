package duke.tasklist;
import duke.dukeexceptions.InvalidArgumentException;
import duke.ui.Ui;
import duke.dukeexceptions.MissingArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Stores tasks in a list
 */
public class TaskList {
    protected Task[] list;
    protected int len;

    /**
     * Constructor for the task list.
     */
    public TaskList() {
        this.list = new Task[100];
        this.len = 0;
    }

    public int getLen(){
        return this.len;
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index index of the task you want to retrieve
     * @return the task
     */
    public Task showTask(int index) {
        return list[index];
    }

    /**
     * Prints out all the task in the task list.
     */
    public String list() {
        String result = "";
        for (int i = 0; i < this.len; i++) {
            int index = i + 1;
            result += "  " + index + ". " + list[i].toString();
        }
        return result;
    }

    /**
     * Marks the task at index as done.
     *
     * @param index index of task to be marked done
     * @return formatted string of the task being marked
     */
    public String setDone(int index) {
        this.list[index].setDone();
        return "  Nice! I've marked this task as done:\n"
                + "    " + list[index].toString();
    }

    /**
     * Unmark the task at index as done.
     *
     * @param index index of task to be set as not done
     * @return formatted string of the task being unmarked
     */
    public String setNotDone(int index) {
        this.list[index].setNotDone();
        return "  OK, I've marked this task as not done:\n"
                + "    " + list[index].toString();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.list[len] = task;
        this.len++;
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param toDo the toDo to be added
     */
    public void addToDo(ToDo toDo) {
        this.list[len] = toDo;
        this.len++;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param deadline Deadline to be added
     */
    public void addDeadline(Deadline deadline) {
        this.list[len] = deadline;
        this.len++;
    }

    /**
     * Adds an Event to the task list.
     *
     * @param event to be added to the list
     */
    public void addEvent(Event event) {
        this.list[len] = event;
        this.len++;
    }

    /**
     * Prints indexed task as a formatted string.
     *
     * @param index the index of the task to be printed
     * @return the task as a formatted string
     */
    public String taskToString(int index) {
        return list[index].toString();
    }

    /**
     * Deletes the indexed task from the list.
     *
     * @param index the index of the task to be deleted
     * @return a formatted string about the deleted task
     */
    public String delete(int index) throws InvalidArgumentException {
        if (index > this.len) {
            throw new InvalidArgumentException("This task doesn't exist");
        }
        Task deletedTask = list[index];

        for (int i = index; i < this.len; i++) {
            this.list[i] = this.list[i + 1];
        }

        this.len--;

        return "  Noted. I've removed this task:\n"
                + "    " + deletedTask.toString()
                + "  Now you have " + this.len + " tasks in the list.\n";

    }

    /**
     * Returns the task list as a formatted string to be saved locally.
     *
     * @return the task list in formatted string
     */
    public String saveTaskList() {
        String result = "";
        for (int i = 0; i < this.len; i++) {
            result += list[i].saveTask() + "\n";
        }
        return result;
    }

    /**
     * Searches for a keyword in the description of tasks in the list to find a match.
     *
     * @param keywords the keywords to be searched.
     */
    public String find(String keywords) {
        TaskList temp = new TaskList();
        for (int i = 0; i < this.len; i++) {
            Task currTask = this.list[i];
            if (currTask.getDescription().contains(keywords)) {
                temp.addTask(currTask);
            }
        }

        String reply = "";
        if (temp.len == 0) {
            reply = "There's nothing in your list that matched keyword\n";
            Ui.displayMessage(reply);
        } else {
            reply = "Here are the matching tasks in your list:\n";
            Ui.displayMessage(reply);
        }
        reply += temp.list();
        return reply;
    }
}
