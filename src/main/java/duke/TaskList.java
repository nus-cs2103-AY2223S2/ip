package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks
 */
public class TaskList implements Serializable {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    TaskList(List<Task> array) {
        this.list = array;
    }

    /**
     * Adds a to-do into the TaskList
     *
     * @param desc Description of to-do
     */
    public void addTodo(String desc) {
        try {
            list.add(new ToDo(desc));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds all events with description fitting the paramter
     *
     * @param desc desc to search by
     * @return new TaskList with searched results
     */
    public TaskList find(String desc) {
        return new TaskList(list.stream().filter((task) -> task.desc.contains(desc)).collect(Collectors.toList()));
    }

    /**
     * Adds an event into the TaskList
     *
     * @param desc Description of event
     * @param from start time of event
     * @param to   end time of event
     * @throws TaskCreationException failure to create event
     */
    public void addEvent(String desc, String from, String to) throws TaskCreationException {
        list.add(new Event(desc, from, to));

    }

    /**
     * Adds a deadline task into the TaskList
     *
     * @param desc     Description of to-do
     * @param deadline deadline of task
     * @throws TaskCreationException failure to create event
     */
    public void addDeadline(String desc, String deadline) throws TaskCreationException {
        list.add(new Deadline(desc, deadline));
    }

    /**
     * Marks a task as done
     *
     * @param index index of task
     * @throws TaskException failure to mark as done
     */
    public void mark(int index) throws TaskException {
        try {
            list.get(index).setIsDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Item not found");
        }
    }

    /**
     * Marks a task as not done
     *
     * @param index index of task
     * @throws TaskException failure to mark as not done
     */
    public void unmark(int index) throws TaskException {
        try {
            list.get(index).setIsDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Item not found");
        }
    }

    /**
     * Deletes a task
     *
     * @param index index of task
     */
    public void delete(int index) {
        list.remove(index);
    }

    public void setPriority(int index, Task.Priority priority) throws TaskException {
        try {
            list.get(index).setPriority(priority);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Item not found");
        }
    }


    @Override
    public String toString() {
        list.sort((a, b) -> b.getPriority().compareTo(a.getPriority()));
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, this.list.get(i)));
        }
        return str.toString();
    }
}
