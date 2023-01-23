import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> itemList = new ArrayList<String>();

        while(flag) {
            userInput = keyboard.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("--------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------");
                flag = false;
            } else if (userInput.equals("list")) {
                System.out.println("--------------------------------");
                for (int i = 0; i < itemList.size(); i ++) {
                    System.out.println((i+1) + ": " + itemList.get(i));

                }
                System.out.println("--------------------------------");

            } else {
                System.out.println("--------------------------------");
                itemList.add(userInput);
                System.out.println("added: " + userInput);
                System.out.println("--------------------------------");

            }
        }
    }
}