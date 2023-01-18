import java.util.*;
import java.lang.*;
public class Duke {
    public static class task{
        char type;
        int id = 0;
        String todo;
        String isDone = "";
        String info;
        public task(char type, int id, String todo, String isDone, String info) {
            this.type = type;
            this.id = id;
            this.todo = todo;
            this.isDone = isDone;
            this.info = info;
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

        while(!comm.equals("bye")) {
            comm = sc.nextLine();
            if(comm.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i < tasklist.size(); i++) {
                    task current = tasklist.get(i);
                    System.out.println(current.id + ".[" +current.type+ "][" + current.isDone + "] "  + current.todo );
                }
                System.out.println(line);
            } else if(comm.startsWith("mark")) {
                String str = comm.substring(comm.length() -1);
                int marking = Integer.parseInt(str);
                task current = tasklist.get(marking-1);
                current.isDone = "X";
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" +current.isDone+ "] " + current.todo);
                System.out.println(line);
            } else if(comm.startsWith("unmark")) {
                String str = comm.substring(comm.length() -1);
                int marking = Integer.parseInt(str);
                task current = tasklist.get(marking-1);
                current.isDone = " ";
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" +current.isDone+ "] " + current.todo);
                System.out.println(line);
            } else if(comm.startsWith("todo")) {
                String doit = comm.substring(5, comm.length());
                tasklist.add(new task('T', tasklist.size()+1, doit, " ", ""));
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + doit);
                System.out.println("Now you have " +tasklist.size()+ " tasks in the list");
                System.out.println(line);
            } else if(comm.startsWith("deadline")) {
                String doit = comm.substring(9, comm.length());
                String[] parts = doit.split("/by");
                tasklist.add(new task('D', tasklist.size()+1, parts[0], " ", parts[1]));
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("   [D][ ] " + parts[0] + "(by:" +parts[1]+")");
                System.out.println("Now you have " +tasklist.size()+ " tasks in the list");
                System.out.println(line);
            } else if(comm.startsWith("event")) {
                String doit = comm.substring(6, comm.length());
                String[] froms = doit.split("/from");
                String[] tos = doit.split("/to");
                tasklist.add(new task('E', tasklist.size()+1, froms[0], " ", froms[1]));
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("   [E][ ] " +froms[0]+ " (from:" +froms[1]+ "to:" +tos[1]);
            }else {
                //tasklist.add(new task('T', tasklist.size()+1, parts[0], " "));
                System.out.println(line);
                System.out.println("added: " + comm);
                System.out.println(line);
            }
        }
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);



    }
}
