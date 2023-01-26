import java.util.Scanner;

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    public static void println(String str) {
        System.out.println(str);
    }

    public static void printGoodbye() {
        println("Sad...Alright bye!");
    }

    public static void printList(TaskList allTasks) {
        if (allTasks.size() == 0) {
            println("You have zero tasks now!");
            return;
        }
        println("Your tasks so far!!");
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.getTask(i);
            String toPrint = String.format("%d. %s", i + 1, task.toString());
            println(toPrint);
        }
    }

    public static void printPrompt() {
        println("Enter your prompt below:");
    }

    public static void printDottedLine() {
        println("----------------------------------------------------");
    }

    public static void printOnStartup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDottedLine();
        println(logo);
        println("Hope you are doing great!\nWhat can I do for you?");
        printDottedLine();
    }

    public static String listen() {
        return sc.nextLine();
    }

}
