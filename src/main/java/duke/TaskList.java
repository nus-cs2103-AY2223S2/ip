package duke;

import java.util.LinkedList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.LinkedList;

/**
 * Class contains variables and methods related to modifying a list of Tasks.
 */
public class TaskList {
    protected LinkedList<Task> lst;

    /**
     * Creates an instance of TaskList.
     */
    public TaskList() {
        this.lst = new LinkedList<>();
    }

    /**
     * Marks the task at the given index of the TaskList as done.
     * @param taskNum index of task to be marked as done in the TaskList.
     * @throws DukeException If index given is out of bounds.
     */
    public void mark(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsDone();
    }

    /**
     * Marks the task at the given index in TaskList as unDone.
     * @param taskNum index of task to be marked as unDone.
     * @throws DukeException If index given is out of bounds.
     */
    public void unmark(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsUndone();
    }

    /**
     * Removes task at given index of TaskList.
     * @param taskNum index of task to be removed.
     * @throws DukeException If index given is out of bounds.
     */
    public void deleteTask(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.lst.remove(taskNum);

    }

    /**
     * Prints all the tasks in the list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i).toString();
            System.out.println(String.format("%d. %s", i + 1, elem));
        }
    }

    /**
     * Prints all tasks in the list which contain the keyword.
     * @param keyword String keyword from find command.
     * @throws DukeException If keyword is empty.
     */
    public void printMatchingList(String keyword) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("empty keyword");
        }
        System.out.println("Here are the matching tasks in your list :)");
        int count = 0;
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                count++;
                System.out.println(String.format("%d. %s", count, task));
            }
        }
    }

    /**
     * Returns task at given index of the list.
     * @param index index of task to be retrieved.
     * @return Task at given index of the list.
     */
    public Task getTask(int index) {
        return this.lst.get(index);
    }

    /**
     * Adds a task to the list.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.lst.add(t);
    }

    /**
     * Parses String into a Task and adds it to the list.
     * @param data contains inputs to creating a Task.
     * @throws DukeException If given input is empty or if the given input is of the wrong format.
     */
    public void addTaskFromString(String data) throws DukeException{
        Task task = null;
        if (data.trim().isEmpty()) {
            throw new DukeException("empty line in file");
        }
        String[] details = data.split(" \\| ");
        // T/D/E || completed || descr || deadline/start || end
        String taskType = details[0];
        String doneData = details[1];
        String descr = details[2];
        try {
            switch (taskType) {
            case "T":
                task = Todo.toTodoFromFileStr(descr, doneData);
                break;
            case "D":
                String deadlineData = details[3];
                task = Deadline.toDeadlineFromFileStr(descr, doneData, deadlineData);
                break;
            case "E":
                String startData = details[3];
                String endData = details[4];
                task = Event.toEventFromFileStr(descr, doneData, startData, endData);
                break;
            default:
                throw new DukeException("none");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("missing details");
        }
        this.lst.add(task);
    }

    /**
     * Returns size of list.
     * @return int size of list.
     */
    public int getSize() {
        return this.lst.size();
    }

    public void printSize() {
        System.out.println(String.format("Now you have %d tasks in the list!", this.getSize()));
    }

}
