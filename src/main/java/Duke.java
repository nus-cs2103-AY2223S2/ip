import java.lang.reflect.Array;
import java.util.*;
public class Duke {

    ArrayList<Task> list;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }


}


