import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello, I'm Duke");
        System.out.pgi rintln("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String s = "";
        while (!bye.equals(s)) {
            s = sc.next();
            System.out.println(s);
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
