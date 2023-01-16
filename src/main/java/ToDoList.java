public class ToDoList {
    private static Task[] todolist = new Task[100];
    private static int count = 0;

    public static void list() {
        System.out.println("\tTasks for Tony Stark:");
        System.out.println("\t--------------------------");
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i + 1) + "." +
                    todolist[i].printTask());
        }
        System.out.println("\t--------------------------");
    }

    // to do
    public static void add(String task) {
        Task t = new Todo(count, task);
        todolist[count] = t;
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\t--------------------------");
    }

    // deadline
    public static void add(String task, String time) {
        Task t = new Deadline(count, task, time);
        todolist[count] = t;
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tDue: " + time);
        System.out.println("\t--------------------------");
    }

    // event
    public static void add(String task, String startTime, String endTime) {
        Task t = new Event(count, task, startTime, endTime);
        todolist[count] = t;
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tStarts: " + startTime);
        System.out.println("\tEnds: " + endTime);
        System.out.println("\t--------------------------");
    }

    public static void markDone (int index) {
        todolist[index - 1].markDone(index);
    }

    public static void unmark(int index) {
        todolist[index - 1].unmark(index);
    }

    public static int getCount() {
        return count;
    }
}
