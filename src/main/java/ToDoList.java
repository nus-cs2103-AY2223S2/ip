import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private List<Task> todolist;
    private int count = 0;

    public ToDoList() {
        todolist = new ArrayList<>();
    }

    public void list() {
        System.out.println("\tTasks for Tony Stark:");
        System.out.println("\t--------------------------");
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i + 1) + "." +
                    todolist.get(i).printTask());
        }
        System.out.println("\t--------------------------");
    }

    public String getTask(int index) {
        return todolist.get(index).printTask();
    }

    // to do
    public void add(String task) {
        Task t = new Todo(count, task);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\t--------------------------");
    }

    // deadline parsed timing
    public void add(String task, LocalDate time) {
        Task t = new Deadline(count, task, time);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tDue: " + time);
        System.out.println("\t--------------------------");
    }

    // event parsed timing
    public void add(String task, LocalDate startTime, LocalDate endTime) {
        Task t = new Event(count, task, startTime, endTime);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tStarts: " + startTime);
        System.out.println("\tEnds: " + endTime);
        System.out.println("\t--------------------------");
    }

    // delete
    public void delete(int index) {
        System.out.println("\t--------------------------");
        System.out.println("\tDeleted: " + todolist.get(index - 1).printTask());
        System.out.println("\t--------------------------");
        todolist.remove(index - 1);
        count--;
    }

    public void markDone (int index) {
        todolist.get(index - 1).markDone(index);
    }

    public void unmark(int index) {
        todolist.get(index - 1).unmark(index);
    }

    public int getCount() {
        return count;
    }
}
