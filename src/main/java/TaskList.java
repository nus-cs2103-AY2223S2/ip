import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    ArrayList<Task> list = new ArrayList<>();

    void addTodo(String desc) {
        try {
            list.add(new ToDo(desc));
            System.out.println("Added: " + desc);
        } catch (TaskCreationException e) {
            System.out.println(e.getMessage());
        }
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, list.get(i)));
        }
        return str.toString();
    }
}
