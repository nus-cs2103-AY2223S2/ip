import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> store;
    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }
    public int getSize() {
        return store.size();
    }
    public ArrayList<Task> getStore() {
        return store;
    }

    public void listTask() {
        int number = 1;
        for (Task stored : store) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }

    public void addTask(Task task) {
        store.add(task);
    }

    public void deleteTask(String num) throws DukeException {
        int index = Integer.parseInt(num) - 1;
        int size = this.getSize();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        System.out.println("Noted. I've removed this task:\n  " +
                task.toString() +
                "\nNow you have " + (size - 1) + " tasks in the list.");
        store.remove(index);
    }

    public void markTask(boolean isMarked, String[] input) throws DukeException {
        int index = Integer.parseInt(input[1]) - 1;
        int size = store.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        task.setChecked(isMarked);
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
        }
    }

}
