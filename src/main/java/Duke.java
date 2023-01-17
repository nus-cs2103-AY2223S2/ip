import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        */

        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting + "\n");

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(goodbye + "\n");

        sc.close();
    }
}
