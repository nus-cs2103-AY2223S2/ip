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
            String cmdtype = cmd.split(" ")[0];
            if (cmdtype.equals("list")) {
                int counter = 1;
                for (Task tmp : lst) {
                    System.out.println(counter++ + ". " + tmp.toString());
                }
            } else if (cmdtype.equals("mark")) {
                int id = Integer.parseInt(cmd.split(" ")[1]);
                Task t = lst.get(id-1);
                t.mark();
                System.out.println("Nice! I've marked this task as done:\n" + t);
            } else if (cmdtype.equals("unmark")) {
                int id = Integer.parseInt(cmd.split(" ")[1]);
                Task t = lst.get(id-1);
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + t);
            } else if (cmdtype.equals("todo")) {
                String activity = cmd.substring(cmdtype.length() + 1);
                Task t = new Todo(activity);
                lst.add(t);
                System.out.println("Got it. I've added this task:\n" + t
                        + "\n Now you have " + lst.size() + " tasks in the list.");
            } else if (cmdtype.equals("deadline")) {
                int indexOfBy = cmd.indexOf("/by ");
                int indexOfDate = indexOfBy + 4; // "/by "
                String activity = cmd.substring(cmdtype.length() + 1, indexOfBy - 1);
                String date = cmd.substring(indexOfDate);
                Task t = new Deadline(activity, date);
                lst.add(t);
                System.out.println("Got it. I've added this task:\n" + t
                        + "\n Now you have " + lst.size() + " tasks in the list.");
            } else if (cmdtype.equals("event")) {
                int indexOfFrom = cmd.indexOf("/from ");
                int indexOfFromTime = indexOfFrom + 6;
                int indexOfTo = cmd.indexOf("/to ");
                int indexOfToTime = indexOfTo + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfFrom - 1);
                String from = cmd.substring(indexOfFromTime, indexOfTo - 1);
                String to = cmd.substring(indexOfToTime);
                Task t = new Event(activity, from, to);
                lst.add(t);
                System.out.println("Got it. I've added this task:\n" + t
                        + "\n Now you have " + lst.size() + " tasks in the list.");
            }
            System.out.println("--------------------------------------------------------------------");
            cmd = input.nextLine();
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------------------------");
    }
}
