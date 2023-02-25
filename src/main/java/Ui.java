import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~";
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showLoadingError(String exceptionMessage) {
        print("error");
    };
    public static void printList(ArrayList<Task> arr) {
        String str = "List:";
        for (int i = 0; i < arr.size(); i++) {
            str += String.format("\n\t%d. %s", i+1, arr.get(i));
        }
        print(str);
    }
    public static void print(String reply) {
        System.out.println(String.format("%s\n%s\n%s", LINE, reply, LINE));
    }
    public static String errorMsg(String error) {
        return String.format("☹ OOPS!!! %s :-(", error);
    }
    /**
     * Used in Level-1 to echo
     */
    public static void echo(String cmd) {
        System.out.println(cmd);
    }
    public static String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }
    public static String greet() {
        return String.format("Hello I am:\n%sWhat can I do for you?", ownName());
    }
    public static void showLine() {
        System.out.println(LINE);
    }
    public static String readCommand() {
//        Scanner sc = new Scanner(System.in);
        String command = this.sc.nextLine();
//        sc.close();
        return command;
    }
    public static void closeUi() {
        this.sc.close();
    }
}
