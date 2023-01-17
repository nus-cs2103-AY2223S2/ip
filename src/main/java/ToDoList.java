import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    //private static Task[] todolist = new Task[100];
    private List<Task> todolist;
    private int count = 0;

    public ToDoList(int size) {
        todolist = new ArrayList<>(size);
    }

    public void list() {
        System.out.println("\tTasks for Tony Stark:");
        System.out.println("\t--------------------------");
        for (int i = 0; i < count; i++) {
            /*
            System.out.println("\t" + (i + 1) + "." +
                    todolist[i].printTask());
             */
            System.out.println("\t" + (i + 1) + "." +
                    todolist.get(i).printTask());
        }
        System.out.println("\t--------------------------");
    }

    // to do
    public void add(String task) {
        Task t = new Todo(count, task);
        //todolist[count] = t;
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\t--------------------------");
    }

    // deadline
    public void add(String task, String time) {
        Task t = new Deadline(count, task, time);
        //todolist[count] = t;
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tDue: " + time);
        System.out.println("\t--------------------------");
    }

    // event
    public void add(String task, String startTime, String endTime) {
        Task t = new Event(count, task, startTime, endTime);
        //todolist[count] = t;
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
        //todolist[index - 1].markDone(index);
        todolist.get(index - 1).markDone(index);
    }

    public void unmark(int index) {
        //todolist[index - 1].unmark(index);
        todolist.get(index - 1).unmark(index);
    }

    public int getCount() {
        return count;
    }
}
