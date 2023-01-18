
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    public static void main(String[] args) {
        String greetings = "Heyyo, Pandora at your service \n"
                + "What can I do for you?";
        System.out.println(greetings);

        //Initial inputs
        Scanner userInput = new Scanner(System.in);
        String userMessage = userInput.nextLine();

        //To-do-list
        ArrayList<String> toDoList = new ArrayList<String>();

        while (true) {
            if (userMessage.equals("bye")) {
                System.out.print("  ");
                System.out.print("Cya~ Till next time!");
                break;
            } else if (userMessage.equals("list")) {
                for(int i = 0; i < toDoList.size(); i++) {
                    System.out.print("  ");
                    System.out.println(i + 1 + ". " + toDoList.get(i));
                }
                Scanner newInput = new Scanner(System.in);
                userMessage = newInput.nextLine();
            } else {
                System.out.print("  ");
                System.out.println("added: " + userMessage);
                toDoList.add(userMessage);
                Scanner newInput = new Scanner(System.in);
                userMessage = newInput.nextLine();
            }
        }

    }
}
