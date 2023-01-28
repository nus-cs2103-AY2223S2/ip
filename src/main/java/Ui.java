import java.util.Scanner;

public class Ui {

    public static void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        showLine();
    }

    public static void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public static void showError(String errorMessage) {
        showLine();
        System.out.println("☹ OOPS!!! " + errorMessage);
        showLine();
    }

    public static String readCommand() {
        Scanner userInput = new Scanner(System.in);
        String newInput = userInput.nextLine();
        return newInput;
    }

    public static void showLoadingError() {
        showError("an error occurred while loading file from given filepath");
    }

    public static void showTask(Task newTask, int numOfTasks) {
        System.out.println(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newTask, numOfTasks));
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showAction(String action) {
        System.out.println(action);
    }

}
