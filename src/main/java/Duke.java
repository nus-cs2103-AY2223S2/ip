import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //greet
        System.out.println("Hello I'm Duke\nWhat can I do for you?");

        Scanner takingInput = new Scanner(System.in);
        String userTyped = takingInput.nextLine();

        while (true) {
            if (userTyped.equals("bye")) {
                //exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                //echo
                System.out.println(userTyped);
                userTyped = takingInput.nextLine();
            }
        }
    }
}
