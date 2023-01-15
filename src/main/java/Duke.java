import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?" + logo);
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.next();
        while (!reply.contains("bye")) {
            System.out.println(reply);
            myObj = new Scanner(System.in);
            reply = myObj.next();
        }
        System.out.println("Bye, Hope to see you again soon!");
    }
}
