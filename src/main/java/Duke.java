import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println("Hello! I am Duke Nice To Meet You\n");
        boolean ongoing = true;

        while(ongoing) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Bye! Hope to See You Again!");
                break;
            }

            System.out.println(command + "\n");
        }
    }
}
