import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("--------------------------------------------------------------------");
        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?");
        System.out.println("--------------------------------------------------------------------");

        ArrayList<Task> lst = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();
        while (!cmd.equals("bye")) {
            try {
                // check if command is valid
                checkCommand(cmd);
                System.out.println("--------------------------------------------------------------------");
                String cmdtype = cmd.split(" ")[0];
                if (cmdtype.equals("list")) {
                    int counter = 1;
                    for (Task tmp : lst) {
                        System.out.println(counter++ + ". " + tmp.toString());
                    }
                } else if (cmdtype.equals("mark")) {
                    int id = Integer.parseInt(cmd.split(" ")[1]);
                    Task t = lst.get(id - 1);
                    t.mark();
                    System.out.println("Nice! I've marked this task as done:\n" + t);
                } else if (cmdtype.equals("unmark")) {
                    int id = Integer.parseInt(cmd.split(" ")[1]);
                    Task t = lst.get(id - 1);
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
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            System.out.println("--------------------------------------------------------------------");
            cmd = input.nextLine();
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------------------------");
    }

    public static boolean checkCommand(String cmd) throws DukeException
    {
        String cmdtype = cmd.split(" ")[0];
        // check that command list must not have any more description
        if (cmdtype.equals("list") && cmdtype.length() != cmd.length()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but list cannot have a description.");
        } else if (cmdtype.equals("todo") && cmdtype.length() == cmd.length()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (cmdtype.equals("deadline")) {
            try {
                int indexOfBy = cmd.indexOf("/by ");
                int indexOfDate = indexOfBy + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfBy - 1);
                String date = cmd.substring(indexOfDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a deadline: deadline {activity} /by {date}.");
            }
        } else if (cmdtype.equals("event")) {
            try {
                int indexOfFrom = cmd.indexOf("/from ");
                int indexOfFromTime = indexOfFrom + 6;
                int indexOfTo = cmd.indexOf("/to ");
                int indexOfToTime = indexOfTo + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfFrom - 1);
                String from = cmd.substring(indexOfFromTime, indexOfTo - 1);
                String to = cmd.substring(indexOfToTime);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a event: event {activity} /from {datetime} /to {datetime}.");
            }
        }
        return true;
    }
}
