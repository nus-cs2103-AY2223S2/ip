public class Ui {

    public Ui() {
    }

    public static void listMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks.get(i).toString());
        }
    }

    public static void markMessage(Task task) {
        System.out.println("Solid! I'm marking this task as done:\n" + task);
    }

    public static void unmarkMessage(Task task) {
        System.out.println("Aight, marking this as not done:\n" + task);
    }

    public static void deleteMessage(Task task) {
        System.out.println("Swee! One less task to go! Removing...\n" + task);
    }

    public static void addMessage(Task task, int taskListSize) {
        System.out.println("Roger. This task has been added:\n" + "  " + task);
        System.out.println("Now you have " + taskListSize + " tasks in your list.");
    }

    public static void welcomeMessage() {
        System.out.println("Hello.\n" + "I am a task organizer!\n");
    }

    public static void farewellMessage() {
        System.out.println("Writing list to storage. Kay thanks bye!");
    }
}
