import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        String userInput;
        boolean flag = true;
        while(flag) {
            userInput = keyboard.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("--------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------");
                flag = false;
            } else {
                System.out.println("--------------------------------");
                System.out.println(userInput);
                System.out.println("--------------------------------");

            }
        }
    }
}
