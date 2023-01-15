public class ToDoList {
    private static Task[] todolist = new Task[100];
    private static int count = 0;

    public static void list() {
        System.out.println("\tHere are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i + 1) + "." +
                    todolist[i].printDone() + " " + todolist[i].printTask());
        }
    }

    public static void add(String task) {
        Task t = new Task(count, task);
        todolist[count] = t;
        count++;
    }

    public static void markDone (int index) {
        todolist[index - 1].markDone();
    }

    public static void unmark(int index) {
        todolist[index - 1].unmark();
    }
}
