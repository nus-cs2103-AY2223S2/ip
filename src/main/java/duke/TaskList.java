package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private final List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    TaskList(List<Task> array) {
        this.list = array;
    }

    void addTodo(String desc) {
        try {
            list.add(new ToDo(desc));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    TaskList find(String desc) {
        return new TaskList(list
                .stream()
                .filter((task) -> task.desc.contains(desc))
                .collect(Collectors.toList()));
    }

    void addEvent(String desc, String from, String to) {
        try {
            list.add(new Event(desc, from, to));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    void addDeadline(String desc, String deadline) {
        try {
            list.add(new Deadline(desc, deadline));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    void mark(int index) {
        try {
            list.get(index).setIsDone(true);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist");
        }
    }

    void unmark(int index) {
        try {
            list.get(index).setIsDone(false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist");
        }
    }

    void delete(int index) {
        list.remove(index);
    }

    List<Task> getList() {
        return list;
    }


}
