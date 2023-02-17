package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.Todos;

/**
 * Stores all the task in an ArrayList. All actions to be performed
 * to the tasks are carried out through the TaskList.
 *
 * @author Cheam Jia Wei
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Gets the task based on the index in the TaskList.
     *
     * @param i Position of the task in the TaskList.
     * @return The task in position i.
     */
    public Task get(int i) {
        assert i > 0;
        return this.taskList.get(i);
    }

    /**
     * Return the size of the TaskList.
     *
     * @return The Size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks the task specified as completed.
     *
     * @param input String that has been input by the user which contains
     *              the position of the task in the TaskList that they want to mark.
     * @return The task that was marked by the program.
     */
    public Task mark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = this.taskList.get(position);
        toChange.mark();
        return toChange;
    }

    /**
     * Unmarks the task specified as completed.
     *
     * @param input String that has been input by the user which contains
     *              the position of the task in the TaskList that they want to unmark.
     * @return The task that was unmarked by the program.
     */
    public Task unmark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = this.taskList.get(position);
        toChange.unmark();
        return toChange;
    }

    /**
     * Creates a new Todos task and inserts it into the TaskList.
     *
     * @param input String that has been input by the user contain the details of the task.
     * @return The newly created Todos task that was added into the TaskList.
     */
    public Task todo(String input) {
        Task toAdd = new Todos(input);
        this.taskList.add(toAdd);
        return toAdd;
    }

    /**
     * Creates a new Deadlines task and inserts it into the TaskList.
     *
     * @param input String that has been input by the user contain the details of the task.
     * @return The newly created Deadlines task that was added into the TaskList.
     */
    public Task deadline(String input) {
        String[] split = input.split(" /by ");
        Task toAdd = new Deadlines(split[0], split[1]);
        this.taskList.add(toAdd);
        return toAdd;
    }

    /**
     * Creates a new Events task and inserts it into the TaskList.
     *
     * @param input String that has been input by the user contain the details of the task.
     * @return The newly created Events task that was added into the TaskList.
     */
    public Task event(String input) {
        String[] split = input.split(" /from | /to ");
        Task toAdd = new Events(split[0], split[1], split[2]);
        this.taskList.add(toAdd);
        return toAdd;
    }

    /**
     * Deletes the task from the TaskList.
     *
     * @param input String that has been input by the user containing the details
     *              of the task to be deleted.
     * @return The task that was deleted from the TaskList.
     */
    public Task delete(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toRemove = this.taskList.remove(position);
        return toRemove;
    }

    /**
     * Lists out the task in the TaskList.
     *
     * @return A string of all the task in the TaskList.
     */
    public String list() {
        String output = "";
        if (this.size() == 0) {
            return "No task currently";
        } else if (this.size() == 1) {
            output += "There is only 1 task currently:\n";
        } else {
            output += "There is a total of " + this.size() + " tasks currently:";
        }
        for (int x = 0; x < this.size(); x++) {
            output += "\n" + (x + 1) + ": " + this.get(x);
        }
        return output;
    }

    /**
     * Goes through the TaskList and returns the tasks happening through
     * the specified date and time.
     *
     * @param date The data and time the user is checking.
     * @return A string listing the tasks occurring through the date and time.
     */
    public String through(LocalDateTime date) {
        int i = 1;
        String output = "Here are the tasks occurring through "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ":";
        for (Task t : taskList) {
            if (t.isWithinDate(date)) {
                output += "\n" + i + "." + t;
                i++;
            }
        }
        return output;
    }

    /**
     * Goes through the TaskList and returns the tasks with the specified keyword.
     *
     * @param input The keyword to be checked.
     * @return A string listing the tasks containing the keyword.
     */
    public String find(String input) {
        int i = 1;
        String output = "Here are the matching tasks in your list:";
        for (Task t : taskList) {
            if (t.doesContain(input)) {
                output += "\n" + i + "." + t;
                i++;
            }
        }
        return output;
    }

    /**
     * Updates the task based on the given inputted details.
     *
     * @param input The details of the task to be updated.
     * @return The updated task.
     * @throws DukeException when the number of the task to be updated does not exist in the TaskList.
     */
    public Task update(String input) throws DukeException {
        String[] split = input.split(" /change ");
        if (taskList.size() < Integer.parseInt(split[0]) || Integer.parseInt(split[0]) < 1) {
            throw new DukeException("Number out of bounds");
        }
        int position = Integer.parseInt(split[0]) - 1;
        Task toUpdate = this.taskList.get(position);
        toUpdate.update(split[1]);
        return toUpdate;
    }

    /**
     * Loads the data from the data file into the TaskList.
     *
     * @param line The String array that was generated by the storage from the data file.
     */
    public void loader(String[] line) {
        String taskType = line[0];
        switch (taskType) {
        case "T": {
            Todos toAdd = new Todos(line[2]);
            if (line[1].equals("1")) {
                toAdd.mark();
            }
            this.taskList.add(toAdd);
        }
        break;

        case "D": {
            Deadlines toAdd = new Deadlines(line[2], line[3]);
            if (line[1].equals("1")) {
                toAdd.mark();
            }
            this.taskList.add(toAdd);
        }
        break;

        case "E": {
            Events toAdd = new Events(line[2], line[3], line[4]);
            if (line[1].equals("1")) {
                toAdd.mark();
            }
            this.taskList.add(toAdd);
        }
        break;

        default:
            assert false : taskType;
            break;
        }
    }
}
