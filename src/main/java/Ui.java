import java.util.Scanner;

public class Ui {

    public static void showWelcome() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void showLine() {
        System.out.println("------------------------------------------------------------------------------");
    }

    public static String readCommand() {
        Scanner sc= new Scanner(System.in);
        String cur = sc.nextLine();
        return cur;
    }

    public void addTask(Task task, int counter) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }

    public void listUI(TaskList list) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < list.getLength(); j++) {
            int k = j + 1;
            System.out.println(k + "." + list.getTask(j).toString());
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    public void mark(Task task) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void DirectoryExceptionUi() {
        System.out.println("____________________________________________________________\n" +
                "  ☹ OOPS!!! Directory not found, please create the directory \"data\" first\n" +
                "____________________________________________________________");
    }

    public static void FileExceptionUi() {
        System.out.println("____________________________________________________________\n" +
                "  ☹ OOPS!!! File not found, please create the file \"duke.txt\" first\n" +
                "____________________________________________________________");
    }

    public static void showError(String error) {
        if (!error.equals("wrong")) {
            System.out.println(error);
        }
        else {
            System.out.println("____________________________________________________________\n" +
                    "  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "____________________________________________________________");
        }
    }


    public void unmark(Task task) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void bye() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void delete(Task task, int counter) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }




}
