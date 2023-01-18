public class Task {
    private static Task[] arr = new Task[100];
    private static int curr = 0;

    private String name;
    private Boolean isChecked;

    private Task(String name) {
        this.name = name;
        this.isChecked = false;
    }

    public static void listTasks() {
        System.out.println("Here are the items in your list: \n");
        for (int i = 0; i < curr; i++) {
            System.out.println(i+1 + ") " + arr[i]);
        }
    }

    public static void addTask(String userInput) {
        arr[curr] = new Task(userInput);
        curr++;
        System.out.println("added: " + userInput);
    }

    public static void markTasks(int task) {
        arr[task].isChecked = true;
        System.out.println("This task is marked as done: \n" + arr[task]);
    }

    public static void unmarkTasks(int task) {
        arr[task].isChecked = false;
        System.out.println("Okay. This task is marked as not done yet: \n" + arr[task]);
    }

    @Override
    public String toString() {
        String mark = this.isChecked ? "[X]" : "[ ]";
        return mark + " " + this.name;
    }
}
