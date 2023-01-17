package Storage;

import LeoException.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final List<Task> data;

    public Storage() {
        this.data = new ArrayList<>();
    }

    public void addTask(Task task) {
        data.add(task);
        System.out.println("Leo: added " + task.getTask() + " to your tasks :-) !");
    }

    public int size() {
        return data.size();
    }

    public void showList() throws NoTaskFoundException {
        try {
            int length = size();
            for (int i = 0; i < length; i++) {
                System.out.println((i + 1) + ". " + getTask(i).toString());
            }
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mark(int num) {
        try {
            getTask(num - 1).mark();
            System.out.println("Leo: Good work! You have completed task " + num + ":");
            System.out.println("     " + getTask(num - 1).toString());
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmark(int num) {
        try {
            getTask(num - 1).unmark();
            System.out.println("Leo: No worries! I have unmarked task " + num + ":");
            System.out.println("     " + getTask(num - 1).toString());
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int num) {
        Task removed;
        try {
            removed = getTask(num -1);
            data.remove(num - 1);
            System.out.println("Leo: I have removed task " + num + ":");
            assert removed != null;
            System.out.println("     " + removed);
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task getTask(int num) throws NoTaskFoundException {
        try {
            return data.get(num);
        } catch (Exception e) {
            throw new NoTaskFoundException("Leo: Hm, this task does not exist...");
        }
    }

}
