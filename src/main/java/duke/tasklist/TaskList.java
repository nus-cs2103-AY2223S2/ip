package duke.tasklist;
import duke.Ui;
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
    public void list() {
        String result = "";
        for (int i = 0; i < this.len; i++) {
            int index = i + 1;
            result += "  " + index + ". " + list[i].toString();
        }
        System.out.print(result);
    }

    /**
     * Marks the task at index as done.
     *
     * @param index index of task to be marked done
     */
    public void setDone(int index) {
        this.list[index].setDone();
        String reply = "  Nice! I've marked this task as done:\n"
                + "    " + list[index].toString();
        System.out.print(reply);
    }

    /**
     * Unmark the task at index as done.
     *
     * @param index index of task to be set as not done
     */
    public void setNotDone(int index) {
        this.list[index].setNotDone();
        String reply = "  OK, I've marked this task as not done:\n"
                + "    " + list[index].toString();
        System.out.print(reply);
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
     * @param description the description of the task.
     */
    public void addToDo(String description) throws MissingArgumentException {
        if (description.trim().equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }

        this.list[len] = new ToDo(description);
        this.len++;

        String reply = "Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param requestContent the additional information needed to create a Deadline
     */
    public void addDeadline(String requestContent) throws MissingArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String description = splitWithBy[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitWithBy.length != 2 || splitWithBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }

        String by = splitWithBy[1].trim();
        this.list[len] = new Deadline(description, by);
        this.len++;

        String reply = "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    /**
     * Adds an Event to the task list.
     *
     * @param requestContent the additional information needed to create an Event
     */
    public void addEvent(String requestContent) throws MissingArgumentException {
        String[] splitFrom = requestContent.split(" /from ", 2);
        String description = splitFrom[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitFrom.length != 2) {
            throw new MissingArgumentException("The from cannot be empty.");
        }

        String[] splitTo = splitFrom[1].split(" /to ", 2);
        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        if (from.equals("")) {
            throw new MissingArgumentException("The from cannot be empty.");
        } else if (splitTo.length != 2 || splitTo[1].trim().equals("")) {
            throw new MissingArgumentException("The to cannot be empty.");
        }

        this.list[len] = new Event(description, from, to);
        this.len++;

        String reply = "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
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
     */
    public void delete(int index) {
        Task deletedTask = list[index];
        for (int i = index; i < this.len; i++) {
            this.list[i] = this.list[i + 1];
        }

        this.len--;
        String reply = "  Noted. I've removed this task:\n"
                + "    " + deletedTask.toString()
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
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
    public void find(String keywords, Ui ui) {
        TaskList temp = new TaskList();
        for (int i = 0; i < this.len; i++) {
            Task currTask = this.list[i];
            if (currTask.getDescription().contains(keywords)) {
                temp.addTask(currTask);
            }
        }

        if (temp.len == 0) {
            ui.displayMessage("There's nothing in your list that matched keyword\n");
        } else {
            ui.displayMessage("Here are the matching tasks in your list:\n");
        }
        temp.list();
    }
}
