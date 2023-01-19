import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
        ArrayList<String> myList = new ArrayList<String>(100);
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < myList.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + myList.get(i));
                }
            }
            else {
                myList.add(userInput);
                System.out.println("added: " + userInput);

            }
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }



}
