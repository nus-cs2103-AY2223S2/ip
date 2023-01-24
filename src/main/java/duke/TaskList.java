package duke;

import duke.task.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a to-do into the TaskList
     *
     * @param desc Description of to-do
     */
    void addTodo(String desc) {
        try {
            list.add(new ToDo(desc));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an event into the TaskList
     *
     * @param desc Description of event
     * @param from start time of event
     * @param to   end time of event
     */
    void addEvent(String desc, String from, String to) {
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
    void addDeadline(String desc, String deadline) {
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
    void mark(int index) {
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
    void unmark(int index) {
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
    void delete(int index) {
        list.remove(index);
    }

    List<Task> getList() {
        return list;
    }


}
