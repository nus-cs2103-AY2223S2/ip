import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
