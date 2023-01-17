
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "_____________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "_____________________________________\n";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while(true){
            if (input.equalsIgnoreCase("bye")){
                System.out.println(
                        "_____________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_____________________________________\n"
                );
                System.exit(0);
            } else {
                System.out.println(
                        "_____________________________________\n"
                        + input + "\n"
                        + "_____________________________________\n"
                );
                input = sc.next();
            }
        }
    }
}
