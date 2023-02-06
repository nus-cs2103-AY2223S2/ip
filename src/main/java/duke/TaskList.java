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
     * @param desc desc to search by
     * @return new TaskList with searched results
     */
    public TaskList find(String desc) {
        return new TaskList(list
                .stream()
                .filter((task) -> task.desc.contains(desc))
                .collect(Collectors.toList()));
    }

    /**
     * Adds an event into the TaskList
     *
     * @param desc Description of event
     * @param from start time of event
     * @param to   end time of event
     */
    public void addEvent(String desc, String from, String to) {
        try {
            list.add(new Event(desc, from, to));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a deadline task into the TaskList
     *
     * @param desc     Description of to-do
     * @param deadline deadline of task
     */
    public void addDeadline(String desc, String deadline) {
        try {
            list.add(new Deadline(desc, deadline));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks a task as done
     *
     * @param index index of task
     */
    public void mark(int index) {
        try {
            list.get(index).setIsDone(true);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist");
        }
    }

    /**
     * Marks a task as not done
     *
     * @param index index of task
     */
    public void unmark(int index) {
        try {
            list.get(index).setIsDone(false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist");
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

    public List<Task> getList() {
        return list;
    }


}
