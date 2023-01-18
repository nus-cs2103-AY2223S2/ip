
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
        String userMessage;

        //To-do-list
        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (true) {
            Scanner userInput = new Scanner(System.in);
            userMessage = userInput.nextLine();
            String [] parts = userMessage.split(" ");

            if (parts[0].equals("bye")) {
                System.out.print("  ");
                System.out.print("Cya~ Till next time!");
                break;
            } else if (parts[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.print("  ");
                    System.out.println(i + 1 + ". " + toDoList.get(i).getStatus());
                }
            } else if (parts[0].equals("mark")) {
                int selection = Integer.parseInt(parts[1]) - 1;
                toDoList.get(selection).mark();
                System.out.println("OK, I've marked this task as done:");
                System.out.println(toDoList.get(selection).getStatus());
            } else if (parts[0].equals("unmark")) {
                int selection = Integer.parseInt(parts[1]) - 1;
                toDoList.get(selection).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(toDoList.get(selection).getStatus());
            } else {
                toDoList.add(new Task(userMessage));
                System.out.print("  ");
                System.out.println("added: " + userMessage);
            }

        }

        //split by first delimiter " "
        //split 2nd token by / delimiter

    }
}
