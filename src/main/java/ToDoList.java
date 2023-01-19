import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>(); //1-based index
    private int toDoCount;
    public ToDoList() {
        arr.add(new Task("0index")); //1-based index
        this.toDoCount = 0;
    }

    public void add(Task task) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        ++this.toDoCount;
        arr.add(task);
        System.out.println(divider + "The Duke has added the following task: \n"
                + " - " + task + "\n"
                + "You now have " + this.toDoCount + " task!\n"+ divider);
    }

    public void delete(int ind) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        Task rm = arr.get(ind);
        arr.remove(ind);
        --this.toDoCount;
        System.out.println(divider + "The Duke has removed the following task: \n"
                + " - " + rm + "\n"
                + "You now have " + this.toDoCount + " task!\n"+ divider);
    }


    public void unmarkTask(int ind) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        arr.get(ind).markNotDone();
        System.out.println(divider + "The Duke has unmarked the following task: \n"
                + " - " + arr.get(ind) + "\n" + divider);
    }

    public void  markTask(int ind) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        arr.get(ind).markDone();
        System.out.println(divider + "The Duke has marked the following task: \n"
                + " - " + arr.get(ind) + "\n" + divider);
    }

    @Override
    public String toString() {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        String output = divider + "TO DO LIST: \n";
        for (int i=1; i<=this.toDoCount; i++) {
            output = output + i + "." + arr.get(i) + "\n";
        }
        output = output + divider;
        return output;
    }
}
