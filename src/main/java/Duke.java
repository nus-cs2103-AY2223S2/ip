import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> lst = new ArrayList<>();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("---------------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println("--------------------------------------------------------------------");
            if (cmd.equals("list")) {
                int counter = 1;
                for (Task tmp : lst) {
                    System.out.println(counter++ + ". " + tmp.toString());
                }
            } else if (cmd.split(" ")[0].equals("mark")) {
                int id = Integer.parseInt(cmd.split(" ")[1]);
                Task t = lst.get(id-1);
                t.mark();
                System.out.println("Nice! I've marked this task as done:\n" + t);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                int id = Integer.parseInt(cmd.split(" ")[1]);
                Task t = lst.get(id-1);
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + t);
            } else {
                System.out.println("added: " + cmd);
                Task t = new Task(cmd);
                lst.add(t);
            }
            System.out.println("--------------------------------------------------------------------");
            cmd = input.nextLine();
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------------------------");
    }
}
