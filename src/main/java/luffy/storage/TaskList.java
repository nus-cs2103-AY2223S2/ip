package luffy.storage;

import java.time.LocalDate;
import java.util.LinkedList;

import luffy.exception.LuffyException;
import luffy.task.Deadline;
import luffy.task.Event;
import luffy.task.Task;
import luffy.task.Todo;

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
     * Adds a task to the list.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.lst.add(t);
    }

    /**
     * Returns task at given index of the list.
     * @param index index of task to be retrieved.
     * @return Task at given index of the list.
     */
    public Task getTask(int index) throws LuffyException {
        if (index < 0 || index > this.getSize() - 1) {
            throw new LuffyException("bounds");
        } else {
            return this.lst.get(index);
        }
    }

    /**
     * Marks the task at the given index of the TaskList as done.
     * @param taskNum index of task to be marked as done in the TaskList.
     * @throws LuffyException If index given is out of bounds.
     */
    public void mark(int taskNum) throws LuffyException {
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new LuffyException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsDone();
    }

    /**
     * Marks the task at the given index in TaskList as unDone.
     * @param taskNum index of task to be marked as unDone.
     * @throws LuffyException If index given is out of bounds.
     */
    public void unmark(int taskNum) throws LuffyException {
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new LuffyException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsUndone();
    }

    /**
     * Removes task at given index of TaskList.
     * @param taskNum index of task to be removed.
     * @throws LuffyException If index given is out of bounds.
     */
    public void deleteTask(int taskNum) throws LuffyException {
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new LuffyException("bounds");
        } else {
            Task t = this.lst.remove(taskNum);
        }
    }

    /**
     * Returns the TaskList as a String.
     * @return String containing all the tasks in the list.
     */
    public String printList() {
        String response = "";
        for (int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i).toString();
            String formattedElem = String.format("%d. %s\n", i + 1, elem);
            response += formattedElem;
        }
        return response;
    }

    /**
     * Returns all tasks in the list which contain the keyword as a String.
     * @param keyword String keyword from find command.
     * @return String containing all tasks in the list which contain the keyword.
     * @throws LuffyException
     */
    public String printMatchingList(String keyword) throws LuffyException {
        if (keyword.isEmpty()) {
            throw new LuffyException("empty keyword");
        }
        String response = "Here are the matching tasks in your list :)\n";
        int count = 0;
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                count++;
                response += String.format("%d. %s\n", count, task);
            }
        }
        return response;
    }

    /**
     * Parses String into a Task and adds it to the list.
     * @param data contains inputs to creating a Task.
     * @throws LuffyException If given input is empty or if the given input is of the wrong format.
     */
    public void addTaskFromString(String data) throws LuffyException {
        Task task = null;
        if (data.trim().isEmpty()) {
            throw new LuffyException("empty line in file");
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
                throw new LuffyException("none");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LuffyException("missing details");
        }
        this.lst.add(task);
    }

    public String printSchedule(LocalDate date) {
        int count = 1;
        String schedule = "";
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.lst.get(i);
            if (task.isTaskInSchedule(date)) {
                schedule += String.format("%d. %s\n", count, task);
            }
        }
        return schedule;
    }

    /**
     * Returns size of list.
     * @return int size of list.
     */
    public int getSize() {
        return this.lst.size();
    }
}
