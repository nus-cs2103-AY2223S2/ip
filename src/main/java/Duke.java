import java.util.*;
public class Duke {
    public static class task{
        int id = 0;
        String todo;
        String isDone = "";
        public task(int id, String todo, String isDone) {
            this.id = id;
            this.todo = todo;
            this.isDone = isDone;
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
                    System.out.println(tasklist.get(i).id + ".[" + tasklist.get(i).isDone + "] "  + tasklist.get(i).todo );
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
            } else {
                tasklist.add(new task(tasklist.size()+1, comm, " "));
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
