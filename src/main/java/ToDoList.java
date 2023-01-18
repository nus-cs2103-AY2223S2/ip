public class ToDoList {
    private Task[] arr = new Task[100]; //1-based indexing
    private int toDoCount;
    public ToDoList() {
        this.toDoCount = 0;
    }

    public void add(Task task) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        ++this.toDoCount;
        arr[toDoCount] = task;
        System.out.println(divider + "The Duke has added the following task: \n"
                + " - " + task + "\n"
                + "You now have " + this.toDoCount + " task!\n"+ divider);
    }

    public void unmarkTask(int ind) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        if (ind > toDoCount || ind <1) {
            System.out.println(divider + "number is out of bounds!\n" + divider);
            return;
        }
        arr[ind].markNotDone();
        System.out.println(divider + "The Duke has unmarked the following task: \n"
                + " - " + arr[ind] + "\n" + divider);
    }

    public void  markTask(int ind) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        if (ind > toDoCount || ind <1) {
            System.out.println(divider + "number is out of bounds!\n" + divider);
            return;
        }
        arr[ind].markDone();
        System.out.println(divider + "The Duke has marked the following task: \n"
                + " - " + arr[ind] + "\n" + divider);
    }

    @Override
    public String toString() {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        String output = divider + "TO DO LIST: \n";
        for (int i=1; i<=this.toDoCount; i++) {
            output = output + i + "." + arr[i] + "\n";
        }
        output = output + divider;
        return output;
    }
}
