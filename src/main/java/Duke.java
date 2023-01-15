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
        Task[] array = new Task[100];
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 0;
        while (!reply.contains("bye")) {
            System.out.println(reply);
            Task action = new Task(reply);
            if (!reply.contains("list") && !reply.contains("mark")) {
                System.out.println("added: " + reply);
                array[count] = action;
                count += 1;

            } else if (reply.contains("unmark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                array[value].unmark();
                System.out.println(" OK, I've marked this task as not done yet:\n ["+array[value].getStatusIcon()+"]" + array[value].description);
            } else if (reply.contains("mark")) {
            int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
            array[value].mark();
            System.out.println("Nice! I've marked this task as done:\n ["+array[value].getStatusIcon()+"]" + array[value].description);
            }
            else if (reply.contains("list")) {
                System.out.println("Here are the tasks in your list:\n");
                int listcount = 1;
                for (Task element: array) {
                    if (element != null) {
                        System.out.println("" + listcount + "." + "[" + element.getStatusIcon() + "]" + element.description);
                    }
                }
            }
            myObj = new Scanner(System.in);
            reply = myObj.nextLine();
        }
        System.out.println("Bye, Hope to see you again soon!");
    }
}


