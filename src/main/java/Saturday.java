import java.util.Scanner;

public class Saturday {
    private static boolean isActive = true;
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        // Greeting
        Utils.divider();
        System.out.println("\t Hello! I'm Saturday\n\t What can I do for you?");
        Utils.divider();
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            try {
                Command command = Command.getCommand(input);
                command.execute(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void output(String text) {
        Utils.divider();
        System.out.println("\t" + text);
        Utils.divider();
        System.out.println("");
    }

    public static void add(String text) {
        Task task = new Task(text);
        taskList.addTask(task);
        output("added: " + text);
    }

    public static void displayList() {
        output(taskList.toString());
    }

    public static void mark(int i) {
        taskList.mark(i);
        output("Nice! I've marked this task as done:\n\t  " + taskList.get(i));
    }

    public static void unMark(int i) {
        taskList.unMark(i);
        output("OK, I've marked this task as not done yet:\n\t  " + taskList.get(i));
    }

    public static void exit() {
        isActive = false;
        output("Bye. Hope to see you again soon!");
    }

}
