public class ToDoList {
    private static String[] todolist = new String[100];
    private static int count = 1;

    public static void list() {
        for (int i = 0; i < count - 1; i++) {
            System.out.println((i + 1) + ". " + todolist[i]);
        }
    }

    public static void add(String task) {
        todolist[count - 1] = task;
        count++;
    }
}
