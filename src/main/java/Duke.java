import java.util.*;
import java.lang.*;
public class Duke {
    public static class task{
        char type;
        String todo;
        String isDone = "";
        String info;
        public task(char type, String todo, String isDone, String info) {
            this.type = type;
            this.todo = todo;
            this.isDone = isDone;
            //this.info = "(" +info+ ")";
            if(info == "") {
                this.info = info;
            } else {
                this.info = "("  +info+ ")";
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        String comm = "";

        List<task> tasklist = new ArrayList<>();
        try {
            while (!comm.equals("bye")) {
                comm = sc.nextLine();
                if (comm.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasklist.size(); i++) {
                        task current = tasklist.get(i);
                        System.out.println(i+1 + ".[" + current.type + "][" + current.isDone + "] " + current.todo);
                    }
                    System.out.println(line);
                } else if (comm.startsWith("mark")) {
                    String str = comm.substring(comm.length() - 1);
                    int marking = Integer.parseInt(str);
                    task current = tasklist.get(marking - 1);
                    current.isDone = "X";
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + current.isDone + "] " + current.todo);
                    System.out.println(line);
                } else if (comm.startsWith("unmark")) {
                    String str = comm.substring(comm.length() - 1);
                    int marking = Integer.parseInt(str);
                    task current = tasklist.get(marking - 1);
                    current.isDone = " ";
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + current.isDone + "] " + current.todo);
                    System.out.println(line);
                } else if(comm.startsWith("delete")) {
                    String str = comm.substring(comm.length() -1);
                    int marking = Integer.parseInt(str);
                    task current = tasklist.get(marking-1);
                    tasklist.remove(marking-1);
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("[" +current.type+ "][" +current.isDone+ "] " +current.todo+ "" +current.info+"");
                    System.out.println("Now you have " +tasklist.size()+ " tasks in the list");
                    System.out.println(line);
                }else if (comm.startsWith("todo")) {
                    String doit = comm.substring(5, comm.length());
                    tasklist.add(new task('T', doit, " ", ""));
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [T][ ] " + doit);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list");
                    System.out.println(line);
                } else if (comm.startsWith("deadline")) {
                    String doit = comm.substring(9, comm.length());
                    String[] parts = doit.split("/by");
                    tasklist.add(new task('D', parts[0], " ", parts[1]));
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   [D][ ] " + parts[0] + "(by:" + parts[1] + ")");
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list");
                    System.out.println(line);
                } else if (comm.startsWith("event")) {
                    String doit = comm.substring(6, comm.length());
                    String[] froms = doit.split("/from");
                    String[] tos = doit.split("/to");
                    tasklist.add(new task('E', froms[0], " ", froms[1]));
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   [E][ ] " + froms[0] + " (from:" + froms[1] + "to:" + tos[1]);
                } else if (!comm.equals("bye")){
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);



    }
}
