import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?" + logo);
        String[] array = new String[100];
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 1;
        while (!reply.contains("bye")) {
            System.out.println(reply);
            if (!reply.contains("list")) {
                System.out.println("added: " + reply);
                array[count] = "" + count + "." + reply;
                count += 1;
            } else {
                for (String element: array) {
                    if (element != null) {
                        System.out.println(element);
                    }
                }
            }
            myObj = new Scanner(System.in);
            reply = myObj.nextLine();
        }
        System.out.println("Bye, Hope to see you again soon!");
    }
}
