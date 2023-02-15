package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.command.Parser;

/**
 * The type Task list.
 */
public class TaskList {

    /**
     * The Tasks.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param storedTasks the stored tasks
     * @throws DukeException the duke exception
     */
    public TaskList(List<String> storedTasks) throws DukeException {
        for (String task: storedTasks) {
            String[] parsedTask = Parser.parseTask(task);
            switch (parsedTask[0]) {
            case "T":
                tasks.add(new Todo(parsedTask[2]));
                break;
            case "D":
                tasks.add(new Deadline(parsedTask[2], LocalDate.parse(parsedTask[3])));
                break;
            case "E":
                tasks.add(new Event(parsedTask[2], parsedTask[3], parsedTask[4]));
                break;
            default:
                throw new DukeException("encountered invalid saved task");
            }
            if (parsedTask[1].equals("1")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }

    }

    /**
     * Add todo.
     *
     * @param description the description
     * @return the string
     * @throws DukeException the duke exception
     */
    public String addTodo(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        return newTodo.toString();
    }

    /**
     * Add deadline.
     *
     * @param description the description
     * @param by          the by
     * @return the string
     * @throws DukeException the duke exception
     */
    public String addDeadline(String description, String by) throws DukeException {
        Deadline newDd = new Deadline(description, LocalDate.parse(by));
        tasks.add(newDd);
        return newDd.toString();
    }

    /**
     * Add event.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     * @return the string
     * @throws DukeException the duke exception
     */
    public String addEvent(String description, String from, String to) throws DukeException {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        return newEvent.toString();
    }

    /**
     * Output TaskList object as a string to view.
     *
     * @return the string
     */
    public String showList() {
        int len = tasks.size();
        if (len == 0) {
            return "";
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < len - 1; i++) {
            result += (i + 1) + "." + tasks.get(i) + "\n";
        }
        result += len + "." + tasks.get(len - 1);
        return result;
    }

    /**
     * Mark Task object as done.
     *
     * @param taskNo the task no
     * @return the string
     */
    public String markTask(int taskNo) {

        assert taskNo < size() && taskNo >= 0;

        tasks.get(taskNo).mark();
        String result = "Nice! I've marked this task as done:\n";
        result += tasks.get(taskNo);
        return result;
    }

    /**
     * Mark Task object as incomplete
     *
     * @param taskNo the task no
     * @return the string
     */
    public String unmarkTask(int taskNo) {

        assert taskNo < size() && taskNo >= 0;

        tasks.get(taskNo).unmark();
        String result = "OK, I've marked this task as not done yet:\n";
        result += tasks.get(taskNo);
        return result;
    }

    /**
     * Delete Task from Tasklist.
     *
     * @param taskNo the task no
     * @return the string
     */
    public String delete(int taskNo) {

        assert taskNo < size() && taskNo >= 0;

        Task deleted = tasks.remove(taskNo);
        String newList = this.showList();
        return String.format("OK, I've deleted: %s\n\n%s",
                deleted, newList);
    }

    /**
     * Gets tasks to save to storage as a List.
     *
     * @return the tasks to save
     */
    public List<String> getTasksToSave() {
        List<String> toSave = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            Task taskToSave = tasks.get(i);
            // add task string in the format {Task Type} | {marked} | {description} | {by}/{from} | {to}
            toSave.add(taskToSave.toSaveableString());
        }
        return toSave;
    }

    /**
     * Returns number of tasks in a TaskList object.
     *
     * @return the int
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns tasks that match search string.
     *
     * @param search word to be searched
     * @return matching tasks from list or failure message
     */
    public String find(String search) {
        String finalTasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.toString().trim().contains(search)) {
                finalTasksString += (i + 1) + "." + tasks.get(i) + "\n";
            }
        }
        if (finalTasksString.isEmpty()) {
            return "sorry, I could not find anything matching. (- _ - ) zzZ";
        }
        return String.format("Here's what I found:\n%s", finalTasksString);
    }

    /**
     * Number of tasks string.
     *
     * @param isMarked boolean to indicate which type of tasks to return
     * @return the string
     */
    public String numberOfTasks(boolean isMarked) {
        String markSymbol = isMarked ? "[X]" : "[ ]";
        long numOfMarkedTasks = tasks.stream()
                .filter(x -> x.getStatusIcon().equals(markSymbol))
                .count();
        return String.format("(- _ - ) zzZ You have %s %d tasks at the moment.\n", isMarked ? "completed" : "not done",
                numOfMarkedTasks);
    }

}

